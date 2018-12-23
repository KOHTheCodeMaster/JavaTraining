package dev.koh.StreamsAPI.practicestreams;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private <T> void displayStreamContent(String nameOfStream, Stream<T> stream) {

        System.out.println("Stream: " + nameOfStream);

        final int SIZE = 10;
        List<T> firstTenElements =
                stream
                        .limit(SIZE)    // returns the Stream with upto n elements (original length if n > the original length).
                        .collect(Collectors.toList());

        System.out.println("Content: ");
        firstTenElements.forEach(System.out::println);
    }

    void generateStreams() {

        System.out.println("Words: " + words);

        displayStreamContent("Words", words.stream());

        //  Stream.of(<T> t) method takes in an varArgs of type T & returns a Stream of those elements.
        Stream<String> songs = Stream.of("The", "Hall", "Of", "Fame");
        displayStreamContent("Songs", songs);

        //  Stream.empty() method returns an Empty Stream.
        Stream<String> empty = Stream.empty();
        displayStreamContent("Empty", empty);

        //  Stream.generate() method creates a Stream of Inifinite Elements.
        Stream<String> echo = Stream.generate(() -> "Echo!");
        displayStreamContent("Echo", echo);

        //  generate method takes in a Supplier Functional Interface.
        Stream<Double> randomDoubles = Stream.generate(Math::random);
        displayStreamContent("Random Doubles", randomDoubles);

        //  Stream.iterate(seed, Predicate or Unary Operator Functional Interface).
        Stream<BigInteger> bigIntegerStream = Stream.iterate(BigInteger.ZERO, num -> num.add(BigInteger.ONE));
        displayStreamContent("BigInteger", bigIntegerStream);

        //  Pattern object has a method splitAsStream that returns the Stream of its elements.
        Stream<String> anotherStream = (Pattern.compile("\\PL+").splitAsStream(text));
        displayStreamContent("Another", anotherStream);

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
