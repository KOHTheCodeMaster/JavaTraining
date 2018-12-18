package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

class ReflectionAPI {

    ReflectionAPI() {
        System.out.println("ReflectionAPI Constructor.");
    }

    void demonstrateReflectionAPI() {
        Student student = new Student("S", "A");

        Class<?> r = student.getClass();
        System.out.println("Current Class: " + r.getName());

        Class<?> s = r.getSuperclass();
        System.out.println("Super Class: " + s.getName());

        Class<?> t = s.getSuperclass();
        System.out.println("Top Class: " + t.getName());

        Package pack = s.getPackage();
        System.out.println("Package Name: " + pack.getName());
    }

}

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


    @Override
    public String toString() {
        return "Name: " + getName() + " | Branch: " + this.branch;
    }

}
