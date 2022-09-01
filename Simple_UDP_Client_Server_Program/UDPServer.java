import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Coded By: Garvit Verma
 **/

public class UDPServer {

    private DatagramSocket serverSocket;
    private InetAddress clientAddress;
    private int clientPort;

    public UDPServer() {

    }

    public UDPServer(int serverPort) throws SocketException {
        serverSocket = new DatagramSocket(serverPort);
    }

    public static void main(String[] args) {
        try {
            UDPServer server = new UDPServer(6666);

            while (true) {
                new Thread(server::receiveMsg).start();
//                server.receiveMsg();
                server.sendMsg();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void receiveMsg() {
        while (true) {
            byte[] rBuffer = new byte[500];
            DatagramPacket response = new DatagramPacket(rBuffer, rBuffer.length);
            try {
                getServerSocket().receive(response);
                setClientAddress(response.getAddress());
                setClientPort(response.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String receivedMsg = new String(rBuffer, 0, response.getLength());
            System.out.println("Client: " + receivedMsg);
        }
    }

    private void sendMsg() {
        while (true) {
            byte[] sBuffer;
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            sBuffer = msg.getBytes();

            DatagramPacket request = new DatagramPacket(sBuffer, sBuffer.length, getClientAddress(), getClientPort());

            try {
                getServerSocket().send(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public DatagramSocket getServerSocket() {
        return serverSocket;
    }

    public InetAddress getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(InetAddress clientAddress) {
        this.clientAddress = clientAddress;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }
}
