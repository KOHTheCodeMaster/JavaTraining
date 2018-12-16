package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

import java.util.ArrayList;

public class SimplifiedGenerics {

    //  In JAVA 7, ArrayList Type in diamond needs to be mentioned only once at the time
    //  of Declaration & doesn't requires to declare it again in the Constructor method.
    ArrayList<Movie> movieList = new ArrayList<>();

    SimplifiedGenerics(){
        System.out.println("SimplifiedGenerics Constructor...");
        initializeMovieList();
    }

    private void initializeMovieList(){

        Movie movie1 = new Movie("3 Idiots", "Comedy");
        Movie movie2 = new Movie("Avengers", "Action");
        Movie movie3 = new Movie("Mowgli", "Animated");

        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);
    }

    void displayMovieList(){
        System.out.println(movieList);
    }
}
