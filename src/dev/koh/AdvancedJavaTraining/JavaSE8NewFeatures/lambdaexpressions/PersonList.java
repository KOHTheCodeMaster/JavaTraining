package dev.koh.AdvancedJavaTraining.JavaSE8NewFeatures.lambdaexpressions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PersonList {

    private List<Person> personList;

    public PersonList() {
        init();
    }

    private void init() {
        personList = new ArrayList<>();

        Person p1 = new Person("P1", 50);
        Person p2 = new Person("P2", 17);
        Person p3 = new Person("P3", 67);

        personList = Arrays.asList(p1, p2, p3);
        System.out.println("People List :\n" + personList);
    }

    void displayPersonListWithoutLambda() {
        Predicate<Person> oldPred = new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return person.getAge() >= 60;
            }
        };

        System.out.println("\nOld People (60+):");
        this.personList.forEach(s -> {
            if (oldPred.test(s))
                System.out.println(s);
        });
    }

    void displayPersonListUsingLambdaExpression() {

        Predicate<Person> oldPred = (p) -> p.getAge() >= 60;
        Predicate<Person> teenPred = (p) -> p.getAge() <= 18;

        //  List could be displayed by simply passing any of the above defined predicates as Arg.
        System.out.println("\nOld People (60+):");
        usePredicateToDisplayList(oldPred);

        System.out.println("\nTeenage People:");
        usePredicateToDisplayList(teenPred);
    }

    private void usePredicateToDisplayList(Predicate<Person> predicate) {
        this.personList.forEach(p -> {
            if (predicate.test(p))
                System.out.println(p);
        });
    }

    //  Member Class.
    class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return getName() + " - " + getAge();
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

}
