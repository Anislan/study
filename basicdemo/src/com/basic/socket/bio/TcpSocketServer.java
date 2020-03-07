package com.basic.socket.bio;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSocketServer {

    public static void main(String[] args) throws IOException {

        // 创建对象并且绑定端口和ip
        int port = 6000;
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket socket = serverSocket.accept();
            new LenthUtil(socket).start();
        }


    }
}
