package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

import java.util.TreeSet;

class UsingTreeSet {

    private TreeSet<Person> treeSet;

    UsingTreeSet() {

        this.treeSet = new TreeSet<>();
    }

    void demonstrateTreeSet() {

        //  TreeSet will contain at most 1 instance of each type i.e. 1 Person & 1 Student.
        //  Multiple instances of Person class or Student class can not be added into the TreeSet
        //  despite any differenes in their properties.

        Person person = new Person("P1");
//        Person person2 = new Person("n1");

        Student student = new Student("S1", "CS");
//        Student student2 = new Student("S2", "EC");

        try {
            treeSet.add(person);
            System.out.println(treeSet.add(student));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(treeSet);

    }

}
