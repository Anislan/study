package com.basic.socket.tcp;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws  IOException {
//        String host ="192.168.50.212";
        String host = "127.0.0.1";
        int port = 6000;
        //流的方式来传输数据
        String message = "Hello Socket!";


        int length;
        try (Socket socket = new Socket(host, port);
             Reader reader = new InputStreamReader(socket.getInputStream());
             Writer writer = new OutputStreamWriter(socket.getOutputStream());
        ) {

            //java向服务器中写入数据
            writer.write(message);
            writer.flush();
            // java从服务器读取数据
//        byte []  bytes = new byte[1024];
            //读取的数据存放
            length = reader.read();
        }
//        int length=inputStream.read(bytes);
        System.out.println("data length:" + length);

        // 先打开后关闭

    }
}
