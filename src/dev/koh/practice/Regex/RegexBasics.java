package dev.koh.practice.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexBasics {

    /*
        private String pattern;
        private String srcText;
        private File srcFile;
        private ArrayList<String> result;

    */
    private int count;

    private Pattern p;
    private Matcher m;

/*
    RegexBasics(String pattern, String srcText) {
        this.pattern = pattern;
        this.srcText = srcText;
        result = new ArrayList<>();

        p = Pattern.compile(pattern);
        m = p.matcher(srcText);

        displayPatternAndSrcText();
    }
*/

/*
    private void displayPatternAndSrcText() {

        System.out.println("Pattern: " + this.pattern);
        System.out.println("Source Text: " + this.srcText);
        System.out.println();

    }
*/

    void findInText(String pattern, String text) {

        this.p = Pattern.compile(pattern);
        this.m = p.matcher(text);

        boolean matchFound = false;
        count = 0;
        while (m.find()) {
            System.out.println("String Matched: \"" + m.group() + "\" @Pos.: " + m.start() +
                    " | Regex: " + pattern + " | Text: " + text);
            count++;

            //  Update matchFound only once when the first match is found.
            if (!matchFound)
                matchFound = true;
        }

        if (matchFound)
            System.out.println("Total Matches Found: " + count);
        else
            System.out.println("No Match Found!");
    }

    void demonstrateSimpleCharacterClasses() {

        /*
         *  Time Stamp: 25th March 2K19, 11:23 AM..!!
         *  '[...]' matches only a Single character enclosed in pair of square brackets.
         *  E.g.: [aeiou] => among all the vowels, it'll match only for one char. i.e. either a, e, i, o or u.
         *  But not more than 1 char. at once.
         *  E.g.: C[oa]t
         *  1.  Cat  => Match!
         *  2.  Cot  => Match!
         *  3.  Coat => No Match!
         *
         *  '^' is meta character that simply represents the negation of the pattern.
         *  It matches with everything in the text except for what the pattern is.
         *  E.g.: [^ae]  |   Care => C is a match, a is not the match (due to ^),
         *                           r is also a match, e again ain't the match.
         */

        findInText("[aeiou]", "Java Tutorials");
        findInText("[bxy]", "Java Tutorials");
        findInText("f[oa]x", "fax machine");
        findInText("f[oa]x", "the quick brown fox");
        findInText("sim[ia]l[iae]r", "catch misspellings like simaler");
        findInText("col[ou]r", "coloring book");
        findInText("col[ou]r", "colouring book");
        findInText("[ou]", "colouring book");

        findInText("[^aeiou]", "Java Tutorials");
        findInText("[^bxy]", "Java Tutorials");
        findInText("f[^oa]x", "fax machine");
        findInText("f[^oa]x", "the quick brown fox");
        findInText("sim[^a]l[^ie]r", "catch misspellings like simaler");
        findInText("col[^ou]r", "coloring book");
        findInText("col[^ou]r", "colouring book");
        findInText("[^ou]", "colouring book");


    }

    void demonstrateRangeOfCharacters() {

        /*
         *  Time Stamp: 25th March 2K19, 11:23 AM..!!
         *  '-' is the meta character that allows the range of characters (inclusive) of the end chars.
         *  E.g.: [a-d] => abcde
         */
        findInText("[a-d]", "Java Tutorials");
        findInText("[A-M]", "Java Tutorials");
        findInText("[1-5]", "5 limes for $1");
        findInText("[^a-d]", "Java Tutorials");
        findInText("[^1-5]", "5 limes for $1");
        findInText("col[o-u]r", "coloring book");
        findInText("col[o-u]r", "colouring book");

    }

}
