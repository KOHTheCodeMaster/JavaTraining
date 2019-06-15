package dev.koh.practice.designPatterns.creational.singleton;

public class SingletonDemo {

    public static void main(String[] args) {

        //  Time Stamp : 15th June 2K19, 05:31 AM..!!

        System.out.println("Begin.");

        SingletonPOJO obj;
        SingletonPOJO obj2;

        obj = SingletonPOJO.getInstance();
        obj.printTimeTaken();

        obj2 = SingletonPOJO.getInstance();
        obj2.printTimeTaken();

        System.out.println("\nEnd.");

    }

}
