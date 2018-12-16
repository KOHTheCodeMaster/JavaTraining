package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

import java.util.ArrayList;

class MovieCollection {

    private ArrayList<Movie> movieList;

    {
        System.out.println("MovieCollection Instance Field Initializer Block.");

        movieList = new ArrayList<>();
        movieList.add(new Movie(MovieTitle.MOWGLI, "Animated"));

    }

    MovieCollection() {
        System.out.println("MovieCollection Constructor.");
    }

    MovieCollection(int n, MovieTitle title, String genre) {
        for (int i = 0; i < n; i++)
            this.movieList.add(new Movie(title, genre));
    }

    void addMovie(MovieTitle title, String genre) {
        this.movieList.add(new Movie(title, genre));
    }

    void displayMovieDetails() {

        //  Using Anonymous Class for one time method invocation.
        //  Parent Class is used for anonymous class, Object class is used as its the Ultimate Super Class.
        new Object() {

            private void openAlbum() {
                System.out.println("Opening Movie Album.");
            }

        }.openAlbum();

        for (Movie movie : movieList) {
            System.out.println(movie.getTitle() + " - " + movie.getGenre());
        }
    }

    int getSize() {
        return movieList.size();
    }

    ArrayList<Movie> getMovieList() {
        return movieList;
    }

    class Movie {

        private MovieTitle title;
        private String genre;

        Movie(MovieTitle title, String genre) {
//        System.out.println("Movie Constructor.");

            this.title = title;
            this.genre = genre;
        }

        MovieTitle getTitle() {
            return title;
        }

        void setTitle(MovieTitle title) {
            this.title = title;
        }

        String getGenre() {
            return genre;
        }

        void setGenre(String genre) {
            this.genre = genre;
        }
    }
}
