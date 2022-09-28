import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

/*
 * Coded by Garvit Verma
 */

public class Server {

    private ServerSocket serverSocket;
    private BufferedReader bReader;
    private DataOutputStream oStream;
    private Socket clientSocket;
    private static InputStream iStream;
    private final static int rbufferSize = 8;
    private CRC crc;
    private GenerateError gError;
    private int totResends;
    private List<Integer> indiviResends;

    public Server(ServerSocket serverSocket, String filePath) {
        this.serverSocket = serverSocket;
        try {
            iStream = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        totResends = 0;
        indiviResends = new ArrayList<>();
    }

    private void startServer() {
        try {
            System.out.println("Server has been started at port number: " + serverSocket.getLocalPort());
            clientSocket = serverSocket.accept();
            bReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            oStream = new DataOutputStream((clientSocket.getOutputStream()));
            while (!serverSocket.isClosed()) {
                byte[] buffer = sendMsg();
                if (buffer == null) {
                    break;
                }
                receiveMsg(buffer);
            }
        } catch (IOException e) {
            System.out.println("Error in opening the server");
            closeConnection(clientSocket, bReader, oStream);
        }
    }

    private byte[] sendMsg() {
        byte[] temp = fileRead();
        if (temp == null) {
            return null;
        } else {
            try {

                StringBuilder sBuilder = new StringBuilder("");
                for (Byte b : temp) {
                    sBuilder.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(" ", "0"));
                }

                crc = new CRC();
                String encodedString = crc.encodeData(sBuilder.toString());
                // byte[] arrToSend = crc.convertToByteArr(encodedString);
                gError = new GenerateError(encodedString);
                String corruptString = gError.createError();
                byte[] arrToSend = crc.convertToByteArr(corruptString);
                oStream.writeInt(arrToSend.length);
                oStream.write(arrToSend);
                oStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return temp;
        }
    }

    private void receiveMsg(byte[] buffer) {
        try {
            String response = bReader.readLine();
            int i = 0;
            while (response.equals("NAK")) {
                sendAgain(buffer);
                response = bReader.readLine();
                totResends++;
                i++;
            }
            indiviResends.add(i);

        } catch (Exception e) {
            closeConnection(clientSocket, bReader, oStream);
        }
    }

    private void sendAgain(byte[] buffer) {
        try {
            StringBuilder sBuilder = new StringBuilder("");
            for (Byte b : buffer) {
                sBuilder.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(" ", "0"));
            }

            crc = new CRC();
            String encodedString = crc.encodeData(sBuilder.toString());
            // byte[] arrToSend = crc.convertToByteArr(encodedString);
            gError = new GenerateError(encodedString);
            String corruptString = gError.createError();
            byte[] arrToSend = crc.convertToByteArr(corruptString);
            oStream.writeInt(arrToSend.length);
            oStream.write(arrToSend);
            oStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] fileRead() {

        try {
            if (iStream.available() != 0) {
                byte[] rbuffer = iStream.readNBytes(rbufferSize);
                return rbuffer;
            } else {
                return null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void closeConnection(Socket clientSocket, BufferedReader bReader, DataOutputStream oStream) {
        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
            if (bReader != null) {
                bReader.close();
            }
            if (oStream != null) {
                oStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connection Closed!");
    }

    public static void main(String[] args) throws IOException {
        int port = 9999;
        ServerSocket serverSocket = new ServerSocket();
        SocketAddress socketAddress = new InetSocketAddress("localhost", port);
        serverSocket.bind(socketAddress);
        Server server = new Server(serverSocket, "ServerData.txt");
        server.startServer();

        System.out.println("Total Resends: " + server.totResends);
        System.out.println("Individual frame resends: " + server.indiviResends);
    }
}
