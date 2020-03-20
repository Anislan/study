package com.basic.reflect.reboot;

public abstract class Reboot implements RebootInterface{

    private String type;

    public Integer capility;

    @Override
    public abstract void action(String command) ;

    private void Other1() {
        System.out.println("reboot other...");
    }

    private void Dance1() {
        System.out.println("reboot dance....");
    }

    @Override
    public String openReboot() {
        return "open success";
    }

    @Override
    public String close() {
        return "close success";
    }

        private void Sing1(){
            System.out.println("reboot sing....");
        }


}
