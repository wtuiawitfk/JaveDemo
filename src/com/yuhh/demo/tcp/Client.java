package com.yuhh.demo.tcp;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 8888);

        //接收服务器端的信息
        Scanner scanner = new Scanner(client.getInputStream());
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }
        scanner.close();
        client.close();
    }
}
