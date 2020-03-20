package com.basic.socket.nio.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class ServerByNIO {

    private static final Logger LOGGER = Logger.getLogger("ServerByNIO");
;
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(60000));
        // 设置通道为非阻塞的
        serverSocketChannel.configureBlocking(false);
        Selector selector=Selector.open();

        // 将serverSocketChannel注册到Selector，Selector监听事件类型为Accept
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            // 等待1s
            if( 0==selector.select(1000)){
                LOGGER.info("没有客户端连接,等待了1s！");
                continue;
            }
             Set<SelectionKey> selectionKeys = selector.keys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if(selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    ByteBuffer byteBuffer=(ByteBuffer) selectionKey.attachment();
                    channel.read(byteBuffer);
                    LOGGER.info("XXX:"+new String(byteBuffer.array()));
                }

                iterator.remove();
            }
        }

    }
}
