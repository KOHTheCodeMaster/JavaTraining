package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

public class Main {

    public static void main(String[] args) {

        System.out.println("Begin.\n");

        UsingHashSet obj = new UsingHashSet();

        Person person = new Person();
        Student student = new Student();

        //  HashSet adds only unique elements i.e. it simply ignores any
        //  duplicate elements & doesn't adds them into the HashSet.
        obj.hashSet.add(person);
        obj.hashSet.add(student);
        obj.hashSet.add(person);

        System.out.println("Size: " + obj.hashSet.size());

        obj.hashSet.add(null);
        System.out.println("Size: " + obj.hashSet.size());

        obj.hashSet.add(student);
        System.out.println("Size: " + obj.hashSet.size());

        obj.hashSet.remove(student);
        System.out.println("Size: " + obj.hashSet.size());

        System.out.println("\nEnd.");

    }
}

/*
 *  Course: Advanced Java Programming - LinkedIn Learning..!!
 *  Mentor: David Gassner.
 *  Date Created: 15th December 2K18 04:58 PM..!!
 *  Last Modified: 18th December 2K18 05:59 PM..!!
 *
 *  Code Developed By,
 *  ~K.O.H..!! ^__^
 */