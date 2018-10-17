package com.zhangshaolin.socket.client;

import java.io.*;
import java.net.Socket;

/**
 * @program: java-study
 * @description: socket客户端
 * @author: zhangshaolin
 * @create: 2018-10-16 18:45
 * <p>
 * 连接建立后，通过使用 I/O 流在进行通信，每一个socket都有一个输出流和一个输入流，
 * 客户端的输出流连接到服务器端的输入流，而客户端的输入流连接到服务器端的输出流。
 **/
public class GreetingClient {

    public static void main(String[] args) {
        startGreetingClient("127.0.0.1", 8080);
    }

    private static void startGreetingClient(String serverName, int port) {
        System.out.println("连接到主机: " + serverName + " , 端口号: " + port);

        try {
            //服务器正在等待时，一个客户端实例化一个 Socket 对象，指定服务器名称和端口号来请求连接。
            //Socket 类的构造函数试图将客户端连接到指定的服务器和端口号。如果通信被建立，则在客户端创建一个 Socket 对象能够与服务器进行通信。
            Socket client = new Socket(serverName, port);
            System.out.println("远程主机地址: " + client.getRemoteSocketAddress());

            //输入流
            OutputStream outToServer = client.getOutputStream();
            //输入流包装类
            DataOutputStream out = new DataOutputStream(outToServer);
            //写入字符串数据
            out.writeUTF("Hello from " + client.getLocalSocketAddress());

            //服务端返回的输出流
            InputStream inFromServer = client.getInputStream();
            //输出流包装类
            DataInputStream in = new DataInputStream(inFromServer);
            //读取数据
            System.out.println("服务器响应: " + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
