package dev.koh.practice.functionalProgramming.basic;

import java.util.Arrays;
import java.util.List;

public class ImperativeVsDeclarative {

    static List<String> ingredients;

    static {
        ingredients = Arrays.asList(
                "Butter",
                "Wheat",
                "Onion",
                "Garlic",
                "Flour",
                "Egg",
                "Chocolate"
        );
    }

    public static void main(String[] args) {

        demonstrateImperativeStyle();

        demonstrateDeclarativeStyle();


    }

    private static void demonstrateImperativeStyle() {
        //  Imperative Programming Style: Emphasized on How To Do it.

        boolean hasOnionOrGarlic = false;
        for (String s : ingredients)
            if (s.equals("Onion") || s.equals("Garlic")) {
                hasOnionOrGarlic = true;
                break;
            }
        if (hasOnionOrGarlic)
            System.out.println("Onion/Garlic is Present!");
        else
            System.out.println("Onion/Garlic ain't Present!");
    }

    private static void demonstrateDeclarativeStyle() {
        //  Declarative Programming Style: Concerned with What To Do, it'll handle the rest.

        if (ingredients.contains("Onion") || ingredients.contains("Garlic"))
            System.out.println("Onion/Garlic is Present!");
        else
            System.out.println("Onion/Garlic ain't Present!");
    }

}
