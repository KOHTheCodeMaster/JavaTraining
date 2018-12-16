package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

import java.text.NumberFormat;

public class NumericLiterals {

    private int num = 1_000_000;    //  1 Million, '_' used to distinguish between the digits.
    private NumberFormat numberFormat;

    NumericLiterals() {
        System.out.println("Numeric Literals Constructor.");
        this.numberFormat = NumberFormat.getInstance();
    }

    public NumericLiterals(int num) {
        this.num = num;
        this.numberFormat = NumberFormat.getInstance();
    }

    void displayNumber() {
        System.out.println(this.numberFormat.format(num));

        numberFormat = NumberFormat.getCurrencyInstance();
        System.out.println(this.numberFormat.format(num));
    }

}
