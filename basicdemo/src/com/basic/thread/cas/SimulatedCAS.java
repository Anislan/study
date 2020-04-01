package com.basic.thread.cas;

/**
 *  模拟CAS操作，等价代码
 */
public class SimulatedCAS
{
    private volatile  int value;

    /**
     *  等价于一条CPU指令（加synchronized的保证原子性）
     * @param expectedValue
     * @param newValue
     * @return
     */
    public synchronized int compareAndSwap(int expectedValue,int newValue){
        int oldValue = value;
        // 比较
        if(oldValue == expectedValue){
            // 交换
            value = newValue;
        }
        return oldValue;
    }
}
