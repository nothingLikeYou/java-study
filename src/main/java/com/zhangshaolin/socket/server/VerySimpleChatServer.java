package com.zhangshaolin.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @program: java-study
 * @description: 简单的聊天服务端程序
 * @author: zhangshaolin
 * @create: 2018-10-22 17:12
 **/
public class VerySimpleChatServer {

    private ArrayList clientOutputStreams;

    public static void main(String[] args) {
        new VerySimpleChatServer().go();
    }

    public class CLientHandler implements Runnable {

        BufferedReader mReader;
        Socket mSocket;

        public CLientHandler(Socket clientSocket) {
            try {
                mSocket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(mSocket.getInputStream());
                mReader = new BufferedReader(isReader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = mReader.readLine()) != null) {
                    System.out.println("read " + message);
                    tellEveryone(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void go() {
        clientOutputStreams = new ArrayList();
        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                Thread t = new Thread(new CLientHandler(clientSocket));
                t.start();
                System.out.println("got a connection");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tellEveryone(String message) {
        Iterator it = clientOutputStreams.iterator();
        while (it.hasNext()) {
            PrintWriter writer = (PrintWriter) it.next();
            writer.println(message);
            writer.flush();
        }
    }

}
