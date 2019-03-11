package dev.koh.competitiveprogramming.codingbat;

public class Recursion {


    private static int factorial(int n) {
    /*
        Given n of 1 or more, return the factorial of n, which is n * (n-1) * (n-2) ... 1.
        Compute the result recursively (without loops).

        factorial(1) → 1
        factorial(2) → 2
        factorial(3) → 6
    */

        if (n < 2)
            return 1;

        return n * (factorial(n - 1));

    }


    private static int bunnyEars(int n) {
        //  Time Stamp: 11th March 2K19, 05:50 PM..!!
        /*
            We have a number of bunnies and each bunny has two big floppy ears.
            We want to compute the total number of ears across all the bunnies recursively
            (without loops or multiplication).

            bunnyEars(0) → 0
            bunnyEars(1) → 2
            bunnyEars(2) → 4
         */

        if (n == 0)
            return 0;
        return 2 + bunnyEars(n - 1);

    }

    private static int fibonacci(int n) {
        /*
            The fibonacci sequence is a famous bit of mathematics, and it happens to
            have a recursive definition. The first two values in the sequence are
            0 and 1 (essentially 2 base cases). Each subsequent value is the sum
            of the previous two values, so the whole sequence is: 0, 1, 1, 2, 3, 5, 8, 13, 21
            and so on. Define a recursive fibonacci(n) method that returns the nth fibonacci number,
            with n=0 representing the start of the sequence.

            fibonacci(0) → 0
            fibonacci(1) → 1
            fibonacci(2) → 1
         */

        if (n == 0)
            return 0;
        if (n == 1)
            return 1;

        //  0 1 1 2 3 5 8 13
        //  0 1 2 3 4 5 6 7
        return fibonacci(n - 1) + fibonacci(n - 2);
        //  06:08 PM
    }

    private static int bunnyEars2(int n) {
        //  Time Stamp: 11th March 2K19, 06:09 PM..!!
        /*
            We have bunnies standing in a line, numbered 1, 2, ...
            The odd bunnies (1, 3, ..) have the normal 2 ears. The even bunnies
            (2, 4, ..) we'll say have 3 ears, because they each have a raised foot.
            Recursively return the number of "ears" in the bunny line 1, 2, ... n
            (without loops or multiplication).

            bunnyEars2(0) → 0
            bunnyEars2(1) → 2
            bunnyEars2(2) → 5
         */

        if (n == 0)
            return 0;
        if (n == 1)
            return 2;

        int temp = (n % 2 == 0) ? 3 : 2;
        return temp + (bunnyEars2(n - 1));
        //  Finish Time: 06:17 PM..!!
    }

    private static int triangle(int n) {
        //  Time Stamp: 11th March 2K19, 06:18 PM..!!

        /*
            We have triangle made of blocks. The topmost row has 1 block,
            the next row down has 2 blocks, the next row has 3 blocks, and so on.
            Compute recursively (no loops or multiplication) the total number of
            blocks in such a triangle with the given number of rows.

            triangle(0) → 0
            triangle(1) → 1
            triangle(2) → 3
         */

        if (n == 0)
            return 0;
        if (n == 1)
            return 1;

        return n + triangle(n - 1);
        //  Finish Time: 06:20 PM..!!

    }

    private static int sumDigits(int n) {
        //  Time Stamp: 11th March 2K19, 06:22 PM..!!
        /*
            Given a non-negative int n, return the sum of its digits recursively (no loops).
            Note that mod (%) by 10 yields the rightmost digit (126 % 10 is 6), while divide (/)
            by 10 removes the rightmost digit (126 / 10 is 12).

            sumDigits(126) → 9
            sumDigits(49) → 13
            sumDigits(12) → 3

         */

        if (n < 10)
            return n;

        int lastDigit = n % 10;
        return lastDigit + sumDigits(n / 10);
        //  Finish Time: 06:26 PM..!!

    }

    private static int count7(int n) {
        //  Time Stamp: 11th March 2K19, 06:27 PM..!!
        /*
            Given a non-negative int n, return the count of the occurrences of 7 as a digit,
            so for example 717 yields 2. (no loops). Note that mod (%) by 10 yields the
            rightmost digit (126 % 10 is 6), while divide (/) by 10 removes the rightmost
            digit (126 / 10 is 12).

            count7(717) → 2
            count7(7) → 1
            count7(123) → 0
         */
        if (n == 7)
            return 1;
        if (n < 7)
            return 0;

        int lastDigit = n % 10;

        return (lastDigit == 7 ? 1 : 0) + count7(n / 10);
        //  Finish Time: 06:32 PM..!!
    }

    private static int count8(int n) {
        //  Time Stamp: 11th March 2K19, 10:14 PM..!!
        /*
            Given a non-negative int n, compute recursively (no loops) the count of the occurrences
            of 8 as a digit, except that an 8 with another 8 immediately to its left counts double, so
            8818 yields 4. Note that mod (%) by 10 yields the rightmost digit (126 % 10 is 6),
            while divide (/) by 10 removes the rightmost digit (126 / 10 is 12).

            count8(8) → 1
            count8(818) → 2
            count8(8818) → 4
         */
        if (n < 8)
            return 0;
        if (n == 8)
            return 1;

        int lastDigit = n % 10;
        int secondLastDigit;
        int temp = 0;

        if (lastDigit == 8) {
            temp++;
            secondLastDigit = (n / 10) % 10;
            if (secondLastDigit == 8)
                temp = 2;
        }
        return temp + count8(n / 10);
        //  Finish Time: 10:24 PM..!!

    }

    public static void main(String[] args) {

//        System.out.println(factorial(3));
//        System.out.println(bunnyEars(13));
//        System.out.println(fibonacci(6));
//        System.out.println(bunnyEars2(4));
//        System.out.println(triangle(4));
//        System.out.println(sumDigits(124));
//        System.out.println(count7(12));
//        System.out.println(count8(8888));

    }

}


/*
 *  Date Created: 11th March 2K19, 05:45 PM..!!
 *  Date Modified: 11th March 2K19, 10:28 PM..!!
 *
 *  Coding Bat - Recursion.
 *
 *  Latest Update:
 *  Init Commit:
 *      Codingbat.com Recursion Problems Solved
 *      Q.1 - Q.8
 *
 *  Code Developed By,
 *  K.O.H..!! ^__^
 *
 */




























