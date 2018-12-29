package dev.koh.AdvancedJavaTraining.JavaSE8NewFeatures.lambdaexpressions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TraversingList {

    private List<String> list;

    TraversingList() {
        list = Arrays.asList("france", "Europe", "new york", "United States Of America");
    }

    void traversalUsingLambdaExpression() {
        System.out.println("Traversing Using forEach method with Lambda Expression: ");
        this.list.forEach(System.out::println);
    }

    void traversalWithoutLambdaExpression() {

        //  Using iterator of the list.
        Iterator iterator = list.iterator();

        System.out.println("\nTraversing Using Iterator: ");
        while (iterator.hasNext())
            System.out.println(iterator.next());

        //  Using for each loop.
        System.out.println("\nTraversing Using For each Loop: ");
        for (String str : list) System.out.println(str);

    }
}
