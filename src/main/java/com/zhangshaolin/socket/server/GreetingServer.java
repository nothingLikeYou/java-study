package com.zhangshaolin.socket.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * @program: java-study
 * @description: socket 服务端
 * @author: zhangshaolin
 * @create: 2018-10-16 18:55
 * 1. Server 先创建一个 serverSocket 来监听8080端口,然后创建一个线程
 * 2. 线程中不断调用阻塞方法 serverSocket.accept() 获取新的连接
 * 3. 当获取到新的连接后,给每条连接创建一个新的线程 这个线程负责从该连接中读取数据
 *
 * 结论: IO 编程模型在客户端较少的情况下运行良好,但是对于客户端比较多的业务来说,单机服务端可能需要支撑成千上万的连接,IO模型可能就不太合适了
 *
 * 原因:
 * 1.线程资源受限:线程是操作系统中   非常宝贵的资源,同一时刻有大量的线程处于阻塞状态是非常严重的资源浪费,操作系统耗不起
 * 2.线程切换效率地下:单机CPU核数固定,线程爆炸之后操作系统频繁进行线程切换,应用性能急剧下降+
 * 3.除了以上两个问题,IO编程中,我们看到数据读写是以字节流为单位
 *
 * 解决以上问题?  NIO 出场  netty出场...
 **/
public class GreetingServer {


    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(8080);

        //接收新连接线程
        new Thread(() -> {

            while (true) {
                System.out.println("等待远程连接,端口号为: " + serverSocket.getLocalPort() + "...");
                try {
                    //阻塞方法获取新的连接
                    Socket server = serverSocket.accept();
                    System.out.println("远程主机地址: " + server.getRemoteSocketAddress());

                    new Thread(() -> {
                        //获取输出流 包装类
                        DataInputStream in = null;
                        try {
                            in = new DataInputStream(server.getInputStream());
                            //读取数据
                            System.out.println(in.readUTF());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                    //获取输入流 输出数据
                    DataOutputStream out = new DataOutputStream(server.getOutputStream());
                    //写入字符串数据
                    out.writeUTF("谢谢连接我: " + server.getLocalSocketAddress() + "\nGoodbye!");
                    //关闭流
                    server.close();
                } catch (SocketTimeoutException e) {
                    System.out.println("Socket timed out!");
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }
}
