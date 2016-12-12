package com.mobyware.sqsClient;

public class AmazonQueueClient{
    public static void main(String[] args){
        printMessage("It worked!!!");
    }

    private static void printMessage(String message){
        System.out.println("Amazon says: " + message);
    }
}