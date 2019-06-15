package dev.koh.libs.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KOHStringUtil {

    public String userInputString(String promptMsg, String options, MyTimer myTimer) {

        String str;
        boolean invalidInput = false;

        do {
            str = null;

            myTimer.pauseTimer();
            try {
                System.out.println(promptMsg);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                str = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            myTimer.continueTimer();

            //  Check for options...
            //  Criteria for deciding whether str is valid or not

            switch (options) {
                case "DEFAULT":
                    invalidInput = false;
                    break;

                case "NOWHITESPACE":
                    assert str != null;
                    boolean foundWhiteSpace = checkWhiteSpace(str);
                    if (foundWhiteSpace) {
                        invalidInput = true;
                        System.out.println("Only White Space found!\nTry Again!");
                    } else {
                        invalidInput = false;
                    }
                    break;

                default:
                    System.out.println("Unknown UserInputString options!");
            }
        } while (invalidInput);

        return str;
    }

    private boolean checkWhiteSpace(String str) {

        //  Return true if str is Empty!
        if (str.isEmpty()) return true;

        //  Return true if any char. of str is found to be White Space Char.
        for (char c : str.toCharArray()) {
            if (!Character.isWhitespace(c)) return false;
        }

        return true;
    }

}
