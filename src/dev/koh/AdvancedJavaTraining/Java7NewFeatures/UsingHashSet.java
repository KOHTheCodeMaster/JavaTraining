package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

import java.util.HashSet;

public class UsingHashSet {

    HashSet<Person> hashSet;

    UsingHashSet() {
        hashSet = new HashSet<>();
    }

    void demonstrateHashSet() {

        Person person = new Person("a");
        Student student = new Student("S4", "B");

        //  HashSet adds only unique elements i.e. it simply ignores any
        //  duplicate elements & doesn't adds them into the HashSet.
        hashSet.add(person);
        hashSet.add(student);
        hashSet.add(person);

        System.out.println("Size: " + hashSet.size());

        hashSet.add(null);
        System.out.println("Size: " + hashSet.size());

        hashSet.add(student);
        System.out.println("Size: " + hashSet.size());

        hashSet.remove(student);
        System.out.println("Size: " + hashSet.size());

        System.out.println(hashSet);

    }

}
