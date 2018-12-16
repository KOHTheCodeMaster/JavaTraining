package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Begin.\n");

        ArrayList<Movie> movies = new MovieCollection(3, "3 Idiots", "Comedy").movieList;

        for (Movie movie : movies) {
            System.out.println(movie.getTitle() + " - " + movie.getGenre());
        }
        System.out.println("\nEnd.");

    }
}

/*
 *  Course: Advanced Java Programming - LinkedIn Learning..!!
 *  Mentor: David Gassner.
 *  Date Created: 15th December 2K18 04:58 PM..!!
 *  Last Modified: 16th December 2K18 04:24 PM..!!
 *
 *  Code Developed By,
 *  ~K.O.H..!! ^__^
 */