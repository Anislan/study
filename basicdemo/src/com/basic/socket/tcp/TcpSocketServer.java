package com.basic.socket.tcp;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSocketServer {

    public static void main(String[] args) throws IOException {

        // 创建对象并且绑定端口和ip
        int port = 6000;
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            try (Socket socket = serverSocket.accept();
                 Reader reader = new InputStreamReader(socket.getInputStream());
                 Writer writer = new OutputStreamWriter(socket.getOutputStream())) {
                System.out.println("客户端已经连接");

                // 获取打印数据
                char[] chars = new char[1024];
                int  length = reader.read(chars);
                String message = new String(chars, 0, length);
                System.out.println(message);

                // 向客户端写入数据
                writer.write(message.length());
                writer.flush();
            }
        }


    }
}
