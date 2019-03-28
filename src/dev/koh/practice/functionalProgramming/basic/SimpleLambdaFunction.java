package dev.koh.practice.functionalProgramming.basic;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class SimpleLambdaFunction {

    private static List<String> ingredients;

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

//        demonstrateImperative();
//        demonstrateDeclarative();
//        demonstrateLambdaFunction();
//        demonstrateMethodReference();
        demonstrateMethodReference2();

    }

    private static void shoutOutLoud(String msg) {
        System.out.println(msg.toUpperCase() + "..!!");
    }

    private static void demonstrateMethodReference2() {

        Consumer<String> speaker = (SimpleLambdaFunction::shoutOutLoud);
        //  feeding each element of the ingredients directly to the shoutOutLoud
        //  which is a static method of SimpleLambdaFunction.

        //  ingredients.forEach(SimpleLambdaFunction::shoutOutLoud);

        ingredients.forEach(speaker);

    }

    private static void demonstrateMethodReference() {

        //  feeding each element of the ingredients directly to the println which is instance method
        //  of the static reference out in System class.
        ingredients.forEach(System.out::println);

    }

    private static void demonstrateImperative() {
        for (String ingredent : ingredients) {
            System.out.println(ingredent);
        }
    }

    private static void demonstrateDeclarative() {

        //  forEach method handled Looping i.e. the order in which the items to iterate.
        ingredients.forEach(new Consumer<String>() {
            @Override
            public void accept(String ingredent) {
                System.out.println(ingredent);
            }
        });

    }

    private static void demonstrateLambdaFunction() {

        Consumer<String> simplePrinter = (str) -> System.out.println(str);
        /*
            Lambda Function => Anonymous Function.
            (args) -> action    |   Data type for args is inferred off the generic type of List.

            ingredients.forEach( (ingredent) -> System.out.println(ingredent) );
         */

        //  forEach method takes in Consumer i.e. Functional Interface which could be passed as a parameter.
        ingredients.forEach(simplePrinter);
    }

}
