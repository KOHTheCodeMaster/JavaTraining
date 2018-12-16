package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

import java.util.ArrayList;

public class MovieCollection {

    static ArrayList<Movie> movieList;

    static {
        System.out.println("MovieCollection Static Initializer Block.");

        movieList = new ArrayList<>();
        movieList.add(new Movie("Mowgli", "Animated"));
        movieList.add(new Movie("3 Idiots", "Comedy"));
        movieList.add(new Movie("Avengers", "Action"));

    }

    MovieCollection() {
        System.out.println("MovieCollection Constructor.");
    }

}
