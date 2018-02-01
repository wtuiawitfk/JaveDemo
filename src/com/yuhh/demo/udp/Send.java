package com.yuhh.demo.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 发送端
 */
public class Send {
    public static void main(String[] args) throws IOException {
        //创建发送端对象
        DatagramSocket sender = new DatagramSocket(10010);
        //准备要发送的信息
        String message = "hello!";
        DatagramPacket dp=new DatagramPacket(message.getBytes(),//要发送数据的字节码
                message.getBytes().length,//要发送数据的字节码长度
                InetAddress.getLocalHost(),//接收端的IP地址
                10086);//接收端的端口
        sender.send(dp);
        sender.close();
    }
}
