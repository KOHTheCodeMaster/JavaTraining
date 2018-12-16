package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

import java.util.ArrayList;

class MovieCollection {

    ArrayList<Movie> movieList;

    {
        System.out.println("MovieCollection Instance Field Initializer Block.");

        movieList = new ArrayList<>();
        movieList.add(new Movie("Mowgli", "Animated"));

    }

    MovieCollection() {
        System.out.println("MovieCollection Constructor.");
    }

    public MovieCollection(int n, String title, String genre) {
        for (int i = 0; i < n; i++)
            this.movieList.add(new Movie(title, genre));
    }
}
