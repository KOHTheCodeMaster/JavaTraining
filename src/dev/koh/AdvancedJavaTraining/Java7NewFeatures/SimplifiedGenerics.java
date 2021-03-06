package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

public class SimplifiedGenerics {

    //  In JAVA 7, ArrayList Type in diamond needs to be mentioned only once at the time
    //  of Declaration & doesn't requires to declare it again in the Constructor method.
    private MovieCollection movieCollection;

    SimplifiedGenerics(){
        System.out.println("SimplifiedGenerics Constructor...");

        movieCollection = new MovieCollection();
        initializeMovieList();
    }

    private void initializeMovieList(){
        movieCollection.addMovie(MovieTitle.THREEIDIOTS, "Comedy");
        movieCollection.addMovie(MovieTitle.HARRYPOTTER, "Animated");
        movieCollection.addMovie(MovieTitle.AVENGERS, "Action");
    }

    void displayMovieList(){
        System.out.println(movieCollection.getMovieList());
    }

    public MovieCollection getMovieCollection() {
        return movieCollection;
    }
}
