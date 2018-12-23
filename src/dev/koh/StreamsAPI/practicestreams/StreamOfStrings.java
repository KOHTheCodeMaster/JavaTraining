package dev.koh.StreamsAPI.practicestreams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class StreamOfStrings {

    private int letterCount;
    private String text;
    private List<String> words;

    //  Predicate is a Functional Interface with only 1 Method i.e. boolean test(Object o); But since
    //  in this case, Predicate is generalized for only String class, it'll take in a String arg. & return boolean.
    //  "\\PL+" is a Regular Expression that considers each non-letter character as a delimeter
    //  and returns only the [AZaz] characters.
    private Predicate<String> longWords = (String s) -> s.length() > this.letterCount;

    StreamOfStrings() {
        text = "Avengers: The infinity war.";
        words = Arrays.asList(text.split("\\PL+"));
        this.letterCount = 5;
    }

    void manipulateLongWords() {

        System.out.println("Words: " + words);

        List<String> contents = selectLongWords();
        System.out.println("List of Long Words (Letter Count >5) : " + contents);

        long longWordsCount = countLongWords();
        System.out.println("Count of Long Words (Letter Count >5) : " + longWordsCount);

    }

    private List<String> selectLongWords() {
        return words
                .stream()   //  Generate the Stream of the elements of the List words.

                //  Transforms the source stream into a new stream with only those elements
                //  of the words that passes the longWords Predicate test.
                .filter(longWords)

                .collect(Collectors.toList());  //  collect the newly generated Stream & convert it into a List.
    }

    private long countLongWords() {
        return words
                .stream()
                .filter(longWords)
                .count();   //  Returns the (long) count of total elements present in the stream.
    }


}
