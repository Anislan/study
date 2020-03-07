package com.basic.socket.bio;

import java.io.*;
import java.net.Socket;

public class LenthUtil extends  Thread {

    private Socket socket;

    public LenthUtil(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
