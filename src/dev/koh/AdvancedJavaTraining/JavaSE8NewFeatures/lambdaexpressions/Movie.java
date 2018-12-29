package dev.koh.AdvancedJavaTraining.JavaSE8NewFeatures.lambdaexpressions;

import java.util.ArrayList;

public class Movie {

    private String title;
    private int numOfStars;

    private Movie(String title, int numOfStars) {
        this.title = title;
        this.numOfStars = numOfStars;
    }

    Movie() {

    }

    private static int compareAges(Movie x, Movie y) {
        Integer a = x.getNumOfStars();
        return a.compareTo(y.getNumOfStars());
    }

    void init() {

        ArrayList<Movie> movieList = new ArrayList<>();

        movieList.add(new Movie("M1", 5));
        movieList.add(new Movie("M2", 1));
        movieList.add(new Movie("M3", 3));

        System.out.println("People List :\n" + movieList);

        sortUsingStaticMethodReference(movieList);
        sortUsingNonStaticMethodReference(movieList);

    }

    private void sortUsingNonStaticMethodReference(ArrayList<Movie> movieList) {
        movieList.sort(this::compareAgesNonStatic);
        System.out.println("Movie List Sorted Using Non-Static Method Reference by " +
                "Lambda Expression:");
        movieList.forEach(System.out::println);

    }

    private void sortUsingStaticMethodReference(ArrayList<Movie> movieList) {

        movieList.sort(Movie::compareAges);
        System.out.println("Movie List Sorted Using Static Method Reference by " +
                "Lambda Expression:");
        movieList.forEach(System.out::println);
    }

    private int compareAgesNonStatic(Movie x, Movie y) {
        Integer a = x.getNumOfStars();
        return a.compareTo(y.getNumOfStars());
    }

    @Override
    public String toString() {
        return getTitle() + " : " + getNumOfStars();
    }

    private String getTitle() {
        return title;
    }

    private int getNumOfStars() {
        return numOfStars;
    }
}
