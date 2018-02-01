package com.yuhh.demo.tcp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("服务端已准备就绪");
        Socket client = server.accept();
        System.out.println(client.getInetAddress());

        //向客户端发送信息
        PrintStream print = new PrintStream(client.getOutputStream());
        String message = "hello!";
        print.println(message);

        print.close();
        server.close();
    }
}
