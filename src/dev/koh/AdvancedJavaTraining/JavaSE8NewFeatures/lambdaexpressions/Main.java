package dev.koh.AdvancedJavaTraining.JavaSE8NewFeatures.lambdaexpressions;

public class Main {

    public static void main(String[] args) {

        //  Demonstrating the use of Calculator Interface i.e.
        //  FunctionalInterface with arguments & a return type.
        demonstrateCalculatorInterface();

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
 *  Time Stamp: 22nd December 2K18, 11:16 PM..!!
 *
 *  Latest Update:  FunctionalInterface with arguments & return type.
 *
 *  Change Log:
 *  1. Functional Interface.
 *
 *  Course: Java SE 8 New Features - LinkedIn Learning..!!
 *      Mentor: David Gassner.
 *
 *  Code Developed By,
 *  ~K.O.H..!! ^__^
 */
