import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Coded By: Garvit Verma
 **/

public class UDPClient {

    private DatagramSocket clientSocket;
    private InetAddress serverAddress;
    private int serverPort;

    public UDPClient() {

    }

    public UDPClient(int clientPort, InetAddress serverAddress, int serverPort) throws SocketException {
        this.clientSocket = new DatagramSocket(clientPort);
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public static void main(String[] args) {
        int clientPort = 6667;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the server address");
        String address = scanner.nextLine();

        System.out.println("Enter the server port number");
        int serverPort = Integer.parseInt(scanner.nextLine());

        try {
            InetAddress serverAddress = InetAddress.getByName(address);
            UDPClient client = new UDPClient(6668, serverAddress, serverPort);

            while (true) {
                new Thread(client::recieveMsg).start();
//                client.recieveMsg();
                client.sendMsg();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void recieveMsg() {
        while (true) {
            byte[] rBuffer = new byte[500];
            DatagramPacket response = new DatagramPacket(rBuffer, rBuffer.length, getServerAddress(), getServerPort());
            try {
                getClientSocket().receive(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String receivedMsg = new String(rBuffer, 0, response.getLength());
            System.out.println("Server: " + receivedMsg);
        }
    }

    private void sendMsg() {
        while (true) {
            byte[] sBuffer;
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            sBuffer = msg.getBytes();

            DatagramPacket request = new DatagramPacket(sBuffer, sBuffer.length, getServerAddress(), getServerPort());

            try {
                getClientSocket().send(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public DatagramSocket getClientSocket() {
        return clientSocket;
    }

    public InetAddress getServerAddress() {
        return serverAddress;
    }

    public int getServerPort() {
        return serverPort;
    }
}
