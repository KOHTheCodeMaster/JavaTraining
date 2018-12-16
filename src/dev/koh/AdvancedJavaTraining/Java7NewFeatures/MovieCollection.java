package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

import java.util.ArrayList;

class MovieCollection {

    private ArrayList<Movie> movieList;

    {
        System.out.println("MovieCollection Instance Field Initializer Block.");

        movieList = new ArrayList<>();
        movieList.add(new Movie("Mowgli", "Animated"));

    }

    MovieCollection() {
        System.out.println("MovieCollection Constructor.");
    }

    MovieCollection(int n, String title, String genre) {
        for (int i = 0; i < n; i++)
            this.movieList.add(new Movie(title, genre));
    }

    void addMovie(String title, String genre) {
        this.movieList.add(new Movie(title, genre));
    }

    void displayMovieDetails() {

        class MovieAlbum {
            void openAlbum() {
                System.out.println("Launching Movie Album!");
            }
        }

        new MovieAlbum().openAlbum();

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

        private String title;
        private String genre;

        Movie(String title, String genre) {
//        System.out.println("Movie Constructor.");

            this.title = title;
            this.genre = genre;
        }

        String getTitle() {
            return title;
        }

        void setTitle(String title) {
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
