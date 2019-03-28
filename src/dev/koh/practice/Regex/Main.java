package dev.koh.practice.Regex;

public class Main {

    public static void main(String[] args) {

        //  Time Stamp: 24th March 2K19, 05:18 PM..!!
        RegexBasics obj = new RegexBasics();
        String result = obj.fixScrapedFName("wh\\at i:s y/o*u/r n<a>m\"e?");
        System.out.println(result);
    }

}
