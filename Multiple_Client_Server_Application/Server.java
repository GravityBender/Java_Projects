package com.assignment2b;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

/**
 * Coded By: Garvit Verma
 **/

public class Server {

    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    private void startServer() {
        boolean flag = false;
        try {
            while(!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New Client has Connected!");
                System.out.println("Client address: " + clientSocket.getInetAddress());

                ClientHandler newClient = new ClientHandler(clientSocket);
                Thread thread = new Thread(newClient);
                thread.start();

//                new Thread(this::sendMsg).start();
                if(!flag) {
                    sendMsg();
                    flag = true;
                }
            }
        } catch (IOException e) {
            closeServer();
        }
    }

    private void sendMsg() {
        new Thread(()->{
            Scanner scanner = new Scanner(System.in);

            while(!ClientHandler.getClientList().isEmpty()) {
                String msg = scanner.nextLine();
                for (ClientHandler clients : ClientHandler.getClientList()) {
                    try {
                        clients.getBufferedWriter().write("Server: " + msg);
                        clients.getBufferedWriter().newLine();
                        clients.getBufferedWriter().flush();
                    } catch (IOException e) {
                        closeServer();
                    }
                }
            }
        }).start();
    }

    private void closeServer() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return this.serverSocket;
    }

    public static void main(String[] args) throws IOException {
        int port = 6666;
        SocketAddress serverAddress = new InetSocketAddress("localhost", port);
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(serverAddress);
        Server server = new Server(serverSocket);
        server.startServer();
    }

}
