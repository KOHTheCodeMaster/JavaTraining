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

        /*
         *  Time Stamp: 18th December 2K18, 07:21 PM..!!
         *  LinkedList implements queue interface which allows it to perform 3 functions.
         *  1. Insert:
         *      a. add(e);  b. offer(e);    {e -> element}
         *  2. Delete:
         *      a. remove();  b. poll();    {returns e element & removes it from the list}
         *  3. Examine:
         *      a. element();   b. peek();  {returns e element}
         *
         *  All a. methods will throw exception if anything goes wrong,
         *  where as all the b. methods will return special values rather throwing the exceptions
         *  & breaking the control flow.
         *  So, b. methods are generally preferred.
         *
         *  peek() & poll() methods will initially start from the 0th index of the linkedlist & are
         *  similar to the peekFirst() or pollFirst() method.
         *
         */

        //  Examine the element from the list.
        //  element() method returns first element of the list, it throws NoSuchElementException if
        //  the list is found to be empty.
        //  peek() method returns first element of the list, but it returns null value if the
        //  list if found to be empty.
        //  Similarly the delete() & poll() methods works as well as the add() & offer() methods.
        //  Hence, peek(), poll(), offer() methods are safe for use to avoid exceptions.

        /*
        element() method can be used as follows tricking it to not throw any exception
        by restricting it to the constraint that the list ain't empty.
        Person stark;
        if(!list.isEmpty()) {
            stark = list.element();
            System.out.println("Removed Element: " + stark.getName());
        }
        */

        Person lannister = list.peek();
        System.out.println("Examining element from the list: " + lannister);
        System.out.println("Size of the List: " + list.size());
        display();

        lannister = list.poll();
        System.out.println("Removed element from the list: " + lannister);
        System.out.println("Size of the List: " + list.size());

        //  Printing the list using iterator for each element's name.
        display();
    }

    private void display() {
        System.out.println("List Order is as follows:");
        Iterator<Person> iterator = list.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next().getName());

//        following approach using for each loop to print each element of the list is also correct.
//        for(Person person : list)
//            System.out.println(person.getName());

    }
}
