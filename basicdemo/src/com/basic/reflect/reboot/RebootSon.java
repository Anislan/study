package com.basic.reflect.reboot;

public class RebootSon extends Reboot{

    private String name;

    public Double money;

    static {
        System.out.println("Hello RebootSon");
    }

    @Override
    public void action(String command) {
        switch (command){
            case  "sing":
                Sing();
                break;
            case "dance":
                Dance();
                break;
            default:
                Other();
                break;

        }
    }

    private void Dance() {
        System.out.println("rebootson dance....");
    }

    private void Sing(){
        System.out.println("rebootson sing....");
    }

    private void Other() {
        System.out.println("rebootson other...");
    }
}
