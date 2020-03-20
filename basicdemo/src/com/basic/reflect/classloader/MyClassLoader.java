package com.basic.reflect.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MyClassLoader extends ClassLoader{

    private String classLoaderName;

    private String path;



    public MyClassLoader(String classLoaderName, String path) {
        this.classLoaderName = classLoaderName;
        this.path = path;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte [] bytes=loadClassData(name);
       return defineClass(name,bytes,0,bytes.length);
    }

    private byte[] loadClassData(String name) {
        name =path+name+".class";
        try (InputStream inputStream = new FileInputStream(name);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ) {
            int data = 0;
            while ((data=inputStream.read())!=-1){
                outputStream.write(data);
            }
            return outputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;

    }


}
