package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

public class Main {

    public static void main(String[] args) {

        System.out.println("Begin.\n");

        //  Underscores '_' can be used in between numbers to distinguish between the digits.
        //  Java doesn't considers the underscores found within the numbers & doesn't prints it out.
        NumericLiterals numericLiterals = new NumericLiterals(10_000_000);

        numericLiterals.displayNumber();

        System.out.println("\nEnd.");

    }
}

/*
 *  Course: Advanced Java Programming - LinkedIn Learning..!!
 *  Mentor: David Gassner.
 *  Date Created: 15th December 2K18 04:58 PM..!!
 *  Last Modified: 16th December 2K18 03:34 PM..!!
 *
 *  Code Developed By,
 *  ~K.O.H..!! ^__^
 */