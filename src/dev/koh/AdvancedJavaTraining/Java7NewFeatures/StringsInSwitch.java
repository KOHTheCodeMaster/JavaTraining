package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

public class StringsInSwitch {

    private SimplifiedGenerics simplifiedGenerics;

    StringsInSwitch() {
        System.out.println("StringsInSwitch Constructor.");

        simplifiedGenerics = new SimplifiedGenerics();

    }

    void displayMovieDetails() {

        for (Movie movie : simplifiedGenerics.movieList) {
            switch (movie.getTitle()) {

                //  Prior to Java 7, switch cases only allowed Primitive Data Types to be used for cases.
                //  But Java 7 allows String Literals to be used & compared within switch cases.

                case "3 Idiots":
                    System.out.println(movie.getTitle() + " - " + movie.getGenre());
                    break;
                case "Avengers":
                    System.out.println(movie.getTitle() + " - " + movie.getGenre());
                    break;
                case "OMG":
                    System.out.println(movie.getTitle() + " - " + movie.getGenre());
                    break;
                case "Harry Potter":
                    System.out.println(movie.getTitle() + " - " + movie.getGenre());
                    break;
            }
        }
    }

}
