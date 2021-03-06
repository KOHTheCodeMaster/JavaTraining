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

