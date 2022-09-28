import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/*
 * Coded by Garvit Verma
 */

public class Client {

    private static OutputStream oStream;
    private Socket socket;
    private DataInputStream iStream;
    private BufferedWriter bufferedWriter;
    private CRC crc;

    public Client(Socket socket, String filePath) {
        try {
            this.socket = socket;

            this.iStream = new DataInputStream(socket.getInputStream());
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            oStream = new FileOutputStream(new File(filePath));

            System.out.println("Connection Successful");
        } catch (IOException e) {
            closeLeaks(socket, iStream, bufferedWriter);
        }
    }

    private void closeLeaks(Socket socket, InputStream iStream, BufferedWriter bufferedWriter) {
        try {
            if (socket != null) {
                socket.close();
            }
            if (iStream != null) {
                iStream.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connection() {
        while (socket.isConnected()) {
            byte[] buffer = receiveMsg();
            if (buffer != null) {
                sendMsg(buffer);
            } else {
                break;
            }
        }
        closeLeaks(socket, iStream, bufferedWriter);
    }

    private byte[] receiveMsg() {
        try {
            int msgLength = iStream.readInt();

            if (msgLength > 0) {
                byte[] buffer = new byte[msgLength];
                iStream.read(buffer);
                return buffer;
            } else {
                return null;
            }
        } catch (IOException e) {
            closeLeaks(socket, iStream, bufferedWriter);
            return null;
        }
    }

    private void sendMsg(byte[] buffer) {
        try {
            StringBuilder sBuilder = new StringBuilder("");
            for (Byte b : buffer) {
                sBuilder.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(" ", "0"));
            }
            crc = new CRC();
            if (crc.checkValid(sBuilder.toString())) {
                bufferedWriter.write("ACK");
                fileWrite(sBuilder.toString());
            } else {
                bufferedWriter.write("NAK");
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            closeLeaks(socket, iStream, bufferedWriter);
        }
    }

    private static void fileWrite(String incomingString) {
        List<Integer> list = new ArrayList<>();

        for (String str : incomingString.split("(?<=\\G.{8})")) {
            list.add(Integer.parseUnsignedInt(str, 2));
        }

        byte[] byteArr = new byte[list.size()];
        for (int i = 0; i < byteArr.length; i++) {
            byteArr[i] = (byte) list.get(i).intValue();
        }

        try {
            oStream.write(byteArr, 0, byteArr.length - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 9999);
        Client client = new Client(socket, "ClientData.txt");
        client.connection();
    }
}
