package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

class Person implements Comparable<Person> {

    private String name;

    Person(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        String x = this.getClass().getSimpleName();
        String y = o.getClass().getSimpleName();
        return x.compareTo(y);
    }

    @Override
    public String toString() {
        return "Name: " + this.name;
    }

    String getName() {
        return name;
    }
}

class Student extends Person {

    private String branch;

    Student(String name, String branch) {
        super(name);
        this.branch = branch;
    }

}


class Officer extends Person {

    private int officerID;

    public Officer(String name, int officerID) {
        super(name);
        this.officerID = officerID;
    }
}

class Employee extends Person {

    private int empID;

    public Employee(String name, int empID) {
        super(name);
        this.empID = empID;
    }
}
