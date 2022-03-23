package com.company;


import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class Main {

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        Semaphore semaphore=new Semaphore(2);
        for(int i=1;i<5;i++){
            try {
                new CallCenterWork("№ "+i,semaphore).start();
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

