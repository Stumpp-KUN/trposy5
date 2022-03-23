package com.company;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CallCenterWork extends Thread {
    private Semaphore semaphore;
    private String name;

    CallCenterWork(String name, Semaphore semaphore) {
        this.semaphore = semaphore;
        this.name = name;
    }

    private Random random = new Random();

    public void run() {
        try {
            if (semaphore.tryAcquire(1, 2000, TimeUnit.MILLISECONDS)) {
                semaphore.acquire();
                System.out.println("Сотруднику колл центра дозванился клиент " + name);
                Thread.sleep(random.nextInt(10000));
                semaphore.release(2);
                System.out.println("Клиент " + name + " получил информацию и положил трубку");
            } else {
                System.out.println("Клиент " + name + " не дождался и положил трубку");
                Thread.sleep(random.nextInt(20000));
                System.out.println("Клиент " + name + " перезванивает");
                run();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
