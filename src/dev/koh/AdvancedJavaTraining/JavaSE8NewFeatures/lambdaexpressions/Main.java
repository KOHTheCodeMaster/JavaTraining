package dev.koh.AdvancedJavaTraining.JavaSE8NewFeatures.lambdaexpressions;

public class Main {

    public static void main(String[] args) {

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
 *  Time Stamp: 22nd December 2K18, 10:44 PM..!!
 *
 *  Functional Interface.
 *  Course: Java SE 8 New Features - LinkedIn Learning..!!
 *      Mentor: David Gassner.
 *
 *  Time Stamp: 22nd December 2K18, 10:44 PM..!!
 *  Code Developed By,
 *  ~K.O.H..!! ^__^
 */
