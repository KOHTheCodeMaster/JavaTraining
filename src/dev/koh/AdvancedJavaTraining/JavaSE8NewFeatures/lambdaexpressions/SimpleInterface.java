package dev.koh.AdvancedJavaTraining.JavaSE8NewFeatures.lambdaexpressions;

/*
    SimpleInterface is an example of the FunctionalInterface as it consists
    of a single abstract method.
    Until Java 7, such interfaces were known as SAM i.e. Single Abstract Method,
    but since Java 8, they're now referred to as FunctionalInterface.
 */
@FunctionalInterface
public interface SimpleInterface {

    //  Simple method to print "Hello World" to the console without the requirements of
    //  any arguments & return type.
    void displayHelloWorld();

}
