package com.basic.socket.nio.net;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class ClientByNIO {

    public static void main(String[] args) throws IOException{
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",60000));
        // 设置非阻塞模式
        socketChannel.configureBlocking(false);
        FileChannel fileChannel = new FileInputStream("E:\\BaiduNetdiskDownload\\learn\\basicdemo\\src\\file\\2.txt").getChannel();
        // 把fileChannel数据拷贝到socketChannel
        fileChannel.transferTo(0,fileChannel.size(),socketChannel);

    }
}
