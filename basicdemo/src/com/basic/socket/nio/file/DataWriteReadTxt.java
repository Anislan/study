package com.basic.socket.nio.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class DataWriteReadTxt {

    public static void main(String[] args) {
//        dataWriteTxt();
//        dataReadTxt();
//        TxtCopyTxt();
//        ChannelCopyChannel();
        mappedTxt();
    }


    private static void mappedTxt() {
        try (
                RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\BaiduNetdiskDownload\\learn\\basicdemo\\src\\file\\1.txt", "rw");
                FileChannel fileChannel = randomAccessFile.getChannel();
        ) {
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
            mappedByteBuffer.put(0, (byte) 'j');
            mappedByteBuffer.put(3, (byte) 'k');
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dataWriteTxt() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("E:\\BaiduNetdiskDownload\\learn\\basicdemo\\src\\file\\1.txt");
             FileChannel fileChannel = fileOutputStream.getChannel();
        ) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put("hello nio!".getBytes());
            // 翻转，将Buffer中postion=0，limit=data.length;
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void ChannelCopyChannel() {
        File sourceFile = new File("E:\\BaiduNetdiskDownload\\learn\\basicdemo\\src\\file\\1.jpg");
        File targetFile = new File("E:\\BaiduNetdiskDownload\\learn\\basicdemo\\src\\file\\2.jpg");
        try (FileInputStream fileInputStream = new FileInputStream(sourceFile);
             FileChannel sourceChannel = fileInputStream.getChannel();
             FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
             FileChannel targetChannel = fileOutputStream.getChannel();
        ) {

            // 使用transerform(),实现通道拷贝
            targetChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void TxtCopyTxt() {
        File sourceFile = new File("E:\\BaiduNetdiskDownload\\learn\\basicdemo\\src\\file\\1.txt");
        File targetFile = new File("E:\\BaiduNetdiskDownload\\learn\\basicdemo\\src\\file\\2.txt");
        try (FileInputStream fileInputStream = new FileInputStream(sourceFile);
             FileChannel sourceChannel = fileInputStream.getChannel();
             FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
             FileChannel targetChannel = fileOutputStream.getChannel();
        ) {

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (true) {
                // 清空Buffer,position=0,limit=capacity
                byteBuffer.clear();
                int sourceLength = sourceChannel.read(byteBuffer);
                if (sourceLength == -1) {
                    break;
                }
//                System.out.println("sourceLength:"+sourceLength+","+new String(byteBuffer.array()));
                // 翻转
                byteBuffer.flip();
                int targetLength = targetChannel.write(byteBuffer);
//                System.out.println("targetLength:"+targetLength+","+new String(byteBuffer.array()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dataReadTxt() {
        File file = new File("E:\\BaiduNetdiskDownload\\learn\\basicdemo\\src\\file\\1.txt");
        try (FileInputStream fileInputStream = new FileInputStream(file);
             FileChannel fileChannel = fileInputStream.getChannel();
        ) {
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
            int length = fileChannel.read(byteBuffer);
            System.out.println("length:" + length + "," + new String(byteBuffer.array()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
