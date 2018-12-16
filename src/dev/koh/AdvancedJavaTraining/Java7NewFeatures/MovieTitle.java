package dev.koh.AdvancedJavaTraining.Java7NewFeatures;

public enum MovieTitle {

    MOWGLI("Mowgli"), THREEIDIOTS("3 Idiots"), AVENGERS("Avengers"), HARRYPOTTER("Harry Potter");

    private String titleAsString;

    MovieTitle(String titleAsString) {
        this.titleAsString = titleAsString;
    }


    @Override
    public String toString() {
        return titleAsString;
    }
}
