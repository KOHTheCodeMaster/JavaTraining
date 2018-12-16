package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

public class Movie {

    private String title;
    private String genre;

    Movie(String title, String genre){
        System.out.println("Movie Constructor.");

        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
