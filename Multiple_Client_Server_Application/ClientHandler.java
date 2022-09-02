package com.assignment2b;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Coded By: Garvit Verma
 **/

public class ClientHandler implements Runnable{

    private Socket clientSocket;
    private static List<ClientHandler> clientList = new ArrayList<>();
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private String username;
    private Server server;

    public ClientHandler(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.username = bufferedReader.readLine();
            clientList.add(this);
            broadcastMsg("Server: " + this.username + " has entered the server");
        } catch (IOException e) {
            closeLeaks(clientSocket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
        while(clientSocket.isConnected()) {
            try {
                String msg = bufferedReader.readLine();
                System.out.println(msg);
                broadcastMsg(msg);
            } catch(IOException e) {
                closeLeaks(clientSocket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    private void broadcastMsg(String msg) {
        for (ClientHandler clients : clientList) {
            try {
                if(!clients.equals(this)) {
                    clients.getBufferedWriter().write(msg);
                    clients.getBufferedWriter().newLine();
                    clients.getBufferedWriter().flush();
                }
            } catch (IOException e) {
                closeLeaks(clientSocket, bufferedReader, bufferedWriter);
            }
        }

//        try {
//            getBufferedWriter().write(msg);
//            getBufferedWriter().newLine();
//            getBufferedWriter().flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void broadcastServerMsg(String msg) {
        for (ClientHandler clients : clientList) {
            try {
                clients.getBufferedWriter().write("Server: " + msg);
                clients.getBufferedWriter().newLine();
                clients.getBufferedWriter().flush();
            } catch (IOException e) {
                closeLeaks(clientSocket, bufferedReader, bufferedWriter);
            }
        }
    }

    private void closeLeaks(Socket clientSocket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClient();
        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeClient() {
        clientList.remove(this);
        broadcastMsg("Server: " + this.username + " has left the server");
    }

    public String getUsername() {
        return username;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public static List<ClientHandler> getClientList() {
        return clientList;
    }
}
