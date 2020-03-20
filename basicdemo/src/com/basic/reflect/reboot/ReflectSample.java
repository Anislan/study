package com.basic.reflect.reboot;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectSample {

    public static void main(String[] args) throws Exception {
        Class rebootSonClass = Class.forName("com.basic.reflect.reboot.RebootSon");

        // 创建RebootSon对象
        // 抽象类不能实例化 Class rebootClass = Class.forName("com.basic.reflect.reboot.Reboot");
        RebootSon reboot =  (RebootSon)rebootSonClass.newInstance();
        System.out.println("reboot class all name:"+rebootSonClass.getName()+",simple name:"+rebootSonClass.getSimpleName());
        System.out.println(reboot.openReboot()+",reboot:"+reboot.getClass());
        System.out.println("==================================================");

        // 获取RebootSon对象的方法，并且执行部分方法
        // 获取类或接口（包括那些由该类或接口声明的以及从超类和超接口继承的那些的类或接口）的公共 member 方法
          Method [] methods = rebootSonClass.getMethods();
        for (Method method:methods) {
            if(method.getName().equals("action")){
                method.invoke(reboot,"sing");
                System.out.println(method.getModifiers()+" "+method.getReturnType().getName()+" "+ method.getName()+"("+method.getParameters().toString()+")");
            }

        }
        System.out.println("==================================================");
        //获取对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法
        Method [] parentMethods =rebootSonClass.getDeclaredMethods();
        for (Method method:parentMethods) {
            if("Dance".equals(method.getName())){
                // 私有方法没有访问权限，会抛异常
                method.setAccessible(true);
                method.invoke(reboot,null);
                System.out.println(method.getModifiers()+" "+method.getReturnType().getName()+" "+ method.getName()+"("+method.getParameters().toString()+")");
            }

        }
        System.out.println("==================================================");
        // 获取父类中所有方法
       Class rebootClass =rebootSonClass.getSuperclass();
        Method  [] rebootMethods=rebootClass.getDeclaredMethods();
        for (Method method:rebootMethods) {
            System.out.println(method.getModifiers()+" "+method.getReturnType().getName()+" "+ method.getName()+"("+method.getParameters().toString()+")");
        }

        System.out.println("==================================================");
        // 设置子类或者父类的私有属性和公共属性值
        Field [] fields=rebootSonClass.getDeclaredFields();
        for (Field field:fields) {
            if("name".equals(field.getName())){
                field.setAccessible(true);
                System.out.println(field.getModifiers()+" "+field.getType().getName() +" "+field.getName()+" "+field.get(reboot));
                field.set(reboot,"zs");
                System.out.println(field.getModifiers()+" "+field.getType().getName() +" "+field.getName()+" "+field.get(reboot));
            }

        }

        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println(System.getProperty("java.class.path"));
    }
}
