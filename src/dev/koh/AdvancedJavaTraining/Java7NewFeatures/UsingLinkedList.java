package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

import java.util.Iterator;
import java.util.LinkedList;

class UsingLinkedList {

    private LinkedList<Person> list;

    UsingLinkedList() {
        this.list = new LinkedList<>();
    }

    void demonstrateLinkedList() {

        Person person = new Person("P1");

        Student student = new Student("S1", "CS");
        Officer officer = new Officer("O1", 101);
        Employee employee = new Employee("E1", 201);

        list.add(student);
        list.add(officer);

        //  Add at the first position of the list i.e. 0th element.
        list.addFirst(person);

        //  Adding at specific position, here at 1st index of the list.
        list.add(1, employee);

        //  Printing the list using toString method in println method.
        System.out.println(list);

        //  Printing the list using iterator for each element's name.
        display();
    }

    void display() {

        Iterator<Person> iterator = list.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next().getName());

//        following approach using for each loop to print each element of the list is also correct.
//        for(Person person : list)
//            System.out.println(person.getName());

    }
}
