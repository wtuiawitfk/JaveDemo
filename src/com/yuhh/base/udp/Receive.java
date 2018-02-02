package com.yuhh.base.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 接收端
 */
public class Receive {
    public static void main(String[] args) throws IOException {
        //创建接收端对象
        DatagramSocket receiver = new DatagramSocket(10086);

        //准备接收缓冲区
        byte[] bytes = new byte[1024];
        //接收数据
        DatagramPacket dp=new DatagramPacket(bytes,1024);

        receiver.receive(dp);

        //把接收到的数据拿出来
        String message = new String(dp.getData(), 0, dp.getLength());
        System.out.println(message);
    }
}
