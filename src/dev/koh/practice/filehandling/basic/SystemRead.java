package dev.koh.practice.filehandling.basic;

import java.io.IOException;

public class SystemRead {

    private static void demonstrateSystemRead() throws IOException {

        //  Buffer Problem.
        f1();

        //  Solved Buffer Problem by conditional looping for '\n' character.
        f2();

        //  Taking Input directly into byte array.
        f3();
    }

    private static void f1() throws IOException {

        System.out.println("Demonstrating : Buffer Problem");
        System.out.println("Enter 1st Character: ");

        int c = System.in.read();   //  returns the integer/ascii value of the input character.
        System.out.println("Input Char.: " + (char) c);

        System.out.println("Enter 2nd Character: ");

        //  Consecutive System.in.read() reads from the inputBuffer,
        //  Since, the end line char. '\n' was already in the buffer so it didn't prompted the user for next input
        //  & simply took that new line char. from the buffer.
        c = System.in.read();
        System.out.println("Input Char.: " + (char) c);

        System.out.println("Hence, Buffer Problem didn't let user enter 2nd character.");
    }

    private static void f2() throws IOException {


        System.out.println("Demonstrating : Solution Of Buffer Problem via Conditional Looping");
        System.out.println("Type Characters: ");

        int c = System.in.read();
        //  System.in.available() method returns the number of bytes that can be read from the buffer.

        System.out.println("Input Char.: ");
        while (System.in.available() > 0) {
            System.out.print((char) c);
            c = System.in.read();
        }

        /*
        while (c != '\n') {
            System.out.println("Input Char.: " + (char) c);
            c = System.in.read();
        }*/

        System.out.println("\nHence, Solved the Buffer Problem.");

    }

    private static void f3() throws IOException {


        System.out.println("\nDemonstrating : Reading directly into Byte Array");
        System.out.println("Type Characters: ");

        byte[] b = new byte[100];

        int i = System.in.read(b);  //  This statement returns the size of the characters read including null char.

        for (byte temp : b) {

            //  Input is terminated after pressing enter i.e. '\n' is the last input char.
            //  but then the null char. '\0' is appended at the end.
            if (temp == '\0')
                break;
            System.out.print((char) temp);

        }
        System.out.println("\nEnd.");
    }

    public static void main(String[] args) throws IOException {

        SystemRead.demonstrateSystemRead();

    }

}

/*
 *  Time Stamp: 23rd March 2K19, 05:40 PM..!!
 *  Java I/O Package.
 *  Self-Practice.
 *
 *  Latest Update:  System.in.read() method.
 *
 *  Code Developed By,
 *  ~K.O.H..!! ^__^
 */