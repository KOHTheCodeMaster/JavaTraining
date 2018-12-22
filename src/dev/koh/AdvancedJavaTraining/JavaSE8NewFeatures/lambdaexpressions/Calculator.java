package dev.koh.AdvancedJavaTraining.JavaSE8NewFeatures.lambdaexpressions;

/*
    Calculator is a FunctionalInterface as it contains only a single abstract method.
    This FunctionalInterface method has 2 arguments & a return type.
 */

@FunctionalInterface
public interface Calculator {

    int calc(int a, int b);
}
