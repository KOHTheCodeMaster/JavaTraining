package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

public class StringsInSwitch {

    private MovieCollection movieCollection;

    StringsInSwitch() {
        System.out.println("StringsInSwitch Constructor.");

        movieCollection = new MovieCollection();

    }

    void displayMovieDetails() {

        for (int i = 0; i < movieCollection.getSize(); i++) {

            MovieTitle title = movieCollection.getMovieList().get(i).getTitle();
            String genre = movieCollection.getMovieList().get(i).getGenre();

            switch (title) {

                //  Prior to Java 7, switch cases only allowed Primitive Data Types to be used for cases.
                //  But Java 7 allows String Literals to be used & compared within switch cases.

                case MOWGLI:
                    System.out.println(title + " - " + genre);
                    break;
                case AVENGERS:
                    System.out.println(title + " - " + genre);
                    break;
                case THREEIDIOTS:
                    System.out.println(title + " - " + genre);
                    break;
                case HARRYPOTTER:
                    System.out.println(title + " - " + genre);
                    break;
            }
        }
    }

}
