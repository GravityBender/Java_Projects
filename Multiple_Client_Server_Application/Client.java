package com.assignment2b;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Coded By: Garvit Verma
 **/

public class Client {

    public Client() {

    }

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.username = username;

            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            closeLeaks(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMsg() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String message = scanner.nextLine();
                bufferedWriter.write(username + ": " + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeLeaks(socket, bufferedReader, bufferedWriter);
        }
    }

    public void receiveMsg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String receivedMsg;
                while (socket.isConnected()) {
                    try {
                        receivedMsg = bufferedReader.readLine();
                        System.out.println(receivedMsg);
                    } catch (IOException e) {
                        closeLeaks(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    private void closeLeaks(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (socket != null) {
                socket.close();
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


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username for the group chat: ");
        String username = scanner.nextLine();
        Socket socket = new Socket("localhost", 6666);
        Client client = new Client(socket, username);
        client.receiveMsg();
        client.sendMsg();
    }

}
