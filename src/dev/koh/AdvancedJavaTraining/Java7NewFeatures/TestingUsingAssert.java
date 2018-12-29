package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

import java.util.Scanner;

enum MathOperator {
    PLUS("+"), MINUS("-"), PRODUCT("*"), DIVISION("/"), MODULUS("%");

    private String operatorAsString;

    MathOperator(String operatorAsString) {
        this.operatorAsString = operatorAsString;
    }

    @Override
    public String toString() {
        return this.operatorAsString;
    }
}

class TestingUsingAssert {

    private int value1;
    private int value2;
    private MathOperator mathOperator;

    void calculate() {

        /*
            Time Stamp: 29th December 2K18, 12:55 PM..!!
            assert is the keyword used for testing for conditions & if evaluated to false will
            return an Exception.
            assert false; //  returns an Exception.
            Any assignment statements for modifying the values of instance variables won't execute.
            value1 = 10;    //  it won't work in method used with assert keyword.
         */

        Scanner scanner = new Scanner(System.in);
        String temp;

        System.out.println("Enter 1st Number: ");
        temp = scanner.next();
        assert validateInput(temp);
        value1 = Integer.parseInt(temp);

        System.out.println("Enter 2nd Number: ");
        temp = scanner.next();
        assert validateInput(temp);
        value2 = Integer.parseInt(temp);

        System.out.println("Enter Math Operator: ");
        temp = scanner.next();
        assert validateOperator(temp);
        setMathOperator(temp);

        int result = getResult(value1, value2, mathOperator);
        System.out.println("Calculating:\n" + value1 + " " + mathOperator + " " + value2 + " = " + result);

    }

    private int getResult(int value1, int value2, MathOperator mathOperator) {

        switch (mathOperator) {
            case PLUS:
                return value1 + value2;
            case MINUS:
                return value1 - value2;
            case DIVISION:
                return value1 / value2;
            case PRODUCT:
                return value1 * value2;
            case MODULUS:
                return value1 % value2;
            default:
                return Integer.MIN_VALUE;
        }

    }

    private boolean setMathOperator(String temp) {
        switch (temp) {
            case "+":
                mathOperator = MathOperator.PLUS;
                return true;
            case "-":
                mathOperator = MathOperator.MINUS;
                return true;
            case "/":
                mathOperator = MathOperator.DIVISION;
                return true;
            case "*":
                mathOperator = MathOperator.PRODUCT;
                return true;
            case "%":
                mathOperator = MathOperator.MODULUS;
                return true;
            default:
                System.out.println("!" + temp);
                return false;
        }
    }

    private boolean validateOperator(String temp) {
        switch (temp) {
            case "+":
            case "-":
            case "/":
            case "*":
            case "%":
                return true;
            default:
                System.out.println("!" + temp);
                return false;
        }
    }

    private boolean validateInput(String temp) {
        try {
            //  Throws NumberFormatException if the temp can't be parsed to Integer.
            int i = (Integer.parseInt(temp));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}