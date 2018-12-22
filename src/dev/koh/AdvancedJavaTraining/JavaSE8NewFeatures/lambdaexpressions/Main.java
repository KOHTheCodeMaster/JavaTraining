package dev.koh.AdvancedJavaTraining.JavaSE8NewFeatures.lambdaexpressions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        //  Demonstrating the use of Runnable Interface i.e.
        //  Implemented by Thread class.
        demonstrateSortList();

    }

    private static void demonstrateSortList() {

        ArrayList<String> cityList = new ArrayList<>();

        cityList.add("france");
        cityList.add("New york");
        cityList.add("europe");
        cityList.add("United states of america");

        sortWithoutLambdaFunction(cityList);
        sortUsingLambdaFunction(cityList);

    }

    private static void sortUsingLambdaFunction(ArrayList<String> cityList) {

        //  Sorting elements without case sensitivity.
        Comparator<String> comparator = String::compareToIgnoreCase;
        Collections.sort(cityList, comparator);
        System.out.println("Using Lambda Expression: " + cityList);

    }

    private static void sortWithoutLambdaFunction(ArrayList<String> cityList) {

        //  Sorting elements with case sensitivity.
        Collections.sort(cityList);
        System.out.println("Case Sensitive Sort: " + cityList);

        //  Sorting elements without case sensitivity.
        Collections.sort(cityList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        System.out.println("Ignoring the Cases:");
        System.out.println("Without Using Lambda Expression: " + cityList);
    }

    private static void demonstrateRunnable() {


        //  Using Local Inner class.
        runnableUsingLocalInnerClass();

        //  Using Lambda Expressions to remove the unnecessary Boiler Play Code.
        runnableUsingLambaExpression();

    }

    private static void runnableUsingLambaExpression() {

        Runnable r1 = () -> System.out.println("Thread 1.");

        Runnable r2 = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 2.");
        };

        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);

        //  Order of execution could be random so thread2 is explicitly made to sleep for 1 second.
        thread1.start();
        thread2.start();

    }

    private static void runnableUsingLocalInnerClass() {

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 1.");
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 2.");
            }
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();

    }

    private static void demonstrateCalculatorInterface() {

        //  Using Local Inner class.
        usingLocalInnerClass(5, 10);

        //  Using Lambda Expressions to remove the unnecessary Boiler Play Code.
        usingLambaExpression(5, 10);

    }

    private static void usingLambaExpression(int x, int y) {

        Calculator add = (a, b) -> a + b;
        Calculator sub = (a, b) -> a - b;
        Calculator mul = (a, b) -> a * b;
        Calculator div = (a, b) -> b != 0 ? a / b : 0;

        System.out.println("Using Lambda Expressions.");
        System.out.println("Add: " + add.calc(x, y));
        System.out.println("Sub: " + sub.calc(x, y));
        System.out.println("Product: " + mul.calc(x, y));
        System.out.println("Division: " + div.calc(x, y));

    }

    private static void usingLocalInnerClass(int x, int y) {

        Calculator add = new Calculator() {
            @Override
            public int calc(int a, int b) {
                return a + b;
            }
        };

        System.out.println("Using Local Inner Class.");
        System.out.println("Addition: " + add.calc(x, y));
    }

    private static void demonstrateSimpleInterface() {
        //  Using Local Inner class.
        usingLocalInnerClass();

        //  Using Lambda Expressions to remove the unnecessary Boiler Play Code.
        usingLambaExpression();
    }

    private static void usingLambaExpression() {

        //  Using empty pair of parenthesis as the functional interface method doesn't
        //  requires any arguments.
        SimpleInterface simpleInterface = () -> System.out.println("Hello World");

        simpleInterface.displayHelloWorld();

    }

    private static void usingLocalInnerClass() {
        SimpleInterface simpleInterface = new SimpleInterface() {
            @Override
            public void displayHelloWorld() {
                System.out.println("Hello World");
            }
        };

        simpleInterface.displayHelloWorld();
    }

}

/*
 *  Time Stamp: 22nd December 2K18, 12:22 PM..!!
 *
 *  Latest Update:  Sort List using Comparator.
 *
 *  Change Log:
 *  1. Functional Interface.
 *  2. FunctionalInterface with arguments & return type.
 *  3. Runnable Interface.
 *
 *  Course: Java SE 8 New Features - LinkedIn Learning..!!
 *      Mentor: David Gassner.
 *
 *  Code Developed By,
 *  ~K.O.H..!! ^__^
 */
