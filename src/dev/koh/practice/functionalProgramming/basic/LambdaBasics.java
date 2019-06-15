package dev.koh.practice.functionalProgramming.basic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

@FunctionalInterface
interface MsgPrinter {
    void printMsg(String msg);
}

@FunctionalInterface
interface StringLengthFinder {
    int strlen(String str);
}

public class LambdaBasics {

    public static void main(String[] args) {

//        MessagePrinterDemo messagePrinterDemo = new MessagePrinterDemo();
//        messagePrinterDemo.majorDemonstration();

//        StringLengthFinderDemo stringLengthFinderDemo = new StringLengthFinderDemo();
//        stringLengthFinderDemo.strlenLambda();

//        RunnableDemo runnableDemo = new RunnableDemo();
//        runnableDemo.demonstrateRunnableInterface();

        PersonListDemo personListDemo = new PersonListDemo();
        personListDemo.major();

    }

}

class PersonListDemo {

    List<Person1> personList;

    Consumer<Person1> printFirstName = (p) -> System.out.println(p.getFirstName());
    Consumer<Person1> printFullName = (p) -> System.out.println(p.getFirstName() + " " + p.getLastName());
    Consumer<Person1> printCompletePersonDetails = (p) -> System.out.println(p);

    PersonListDemo() {
        personList = Arrays.asList(
                new Person1("Navin", "Reddy", 28),
                new Person1("John", "Purcell", 30),
                new Person1("Tim", "Buchalka", 35),
                new Person1("Hitesh", "Choudhary", 32)
        );
    }

    void major() {

        //  Sort the personList by their firstName
        sortPersonList(personList);

        //  Display personList
        System.out.println("Complete Person Details of all persons are:\n");
        printPersonConditionally(personList, p -> true, printCompletePersonDetails);

        //  Display specific persons which passes different tests.
        displayPersonWithTests();
    }

    private void sortPersonList(List<Person1> personList) {
        Comparator<Person1> comparator = (p1, p2) ->
                (p1.getFirstName().compareTo(p2.getFirstName()));
        personList.sort(comparator);

    }

    private void displayPersonWithTests() {

        String strStartsWith = "J";
        System.out.println("\nPersons whose first name starts with \"" + strStartsWith + "\" are:");
        //        Old / Alternative method
        //        printPersonsBeginningWithstr(personList, strStartsWith);

        //  print person's firstName which begins with strStartsWith
        printPersonConditionally(personList, (p) -> (p.getFirstName().startsWith(strStartsWith)), printFirstName);


        //  print person's full name which ends with strEndsWith
        String strEndsWith = "a";
        System.out.println("\nPersons Full Names whose last name ends with \"" + strEndsWith + "\" are:");
        printPersonConditionally(personList, (p) -> (p.getLastName().endsWith(strEndsWith)), printFullName);

    }

    private void printPersonConditionally(List<Person1> personList, PersonalCriteria personalCriteria, Consumer consumer) {

        for (Person1 p : personList)
            if (personalCriteria.testIndividual(p))
                consumer.accept(p);

    }

    private void printPersonsBeginningWithstr(List<Person1> personList, String str) {

        Consumer<Person1> printPersonStartsWithStr = (person) -> {
            if (person.getFirstName().startsWith(str))
                System.out.println(person);
        };
        personList.forEach(printPersonStartsWithStr);

    }

    interface PersonalCriteria {
        boolean testIndividual(Person1 person);
    }

}

class Person1 {

    private String firstName;
    private String lastName;
    private int age;

    Person1(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " : " + age + " yrs.";
    }
}

class RunnableDemo {

    void demonstrateRunnableInterface() {

        Runnable runnable = () -> System.out.println("Thread Started.");

        Thread thread = new Thread(runnable);
        thread.start();

    }

}


//  Interfaces

class StringLengthFinderDemo {

    void strlenLambda() {
        String s = "Hello World..!!";

        StringLengthFinder stringLengthFinder = (str) -> str.length();
        int length = stringLengthFinder.strlen(s);

        //  Print the length using msgPrinter
        MsgPrinter msgPrinter = (len) -> System.out.println(len);
        msgPrinter.printMsg(length + "");
    }

}

class MessagePrinterDemo {

    void majorDemonstration() {
        demonstrateAnonymousClass();
        demonstrateLocalInnerStaticClass();
        demonstrateLambdaExpression();
    }

    private void demonstrateAnonymousClass() {

        //  Anonymous Class
        print(new MsgPrinter() {
            @Override
            public void printMsg(String msg) {
                System.out.println(msg);
            }
        });

    }

    private void demonstrateLocalInnerStaticClass() {

        //  LocalInnerStaticClass
        class LocalInnerStaticClass implements MsgPrinter {

            @Override
            public void printMsg(String msg) {
                System.out.println(msg);
            }
        }

        LocalInnerStaticClass objLocal = new LocalInnerStaticClass();
        print(objLocal);

    }

    private void demonstrateLambdaExpression() {

        //  Lambda Expression
        MsgPrinter msgPrinter = (msg) -> System.out.println(msg);
        print(msgPrinter);

    }

    private void print(MsgPrinter msgPrinter) {
        String msg = "Hello World..!!";
        msgPrinter.printMsg(msg);
    }

}