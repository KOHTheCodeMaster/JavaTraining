package dev.koh.practice.filehandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadUrlFile {

    private ArrayList<String> urlList = new ArrayList<>();
    private String urlFilePath;

    ReadUrlFile(String urlFilePath) {
        this.urlFilePath = urlFilePath;
    }

    public static void main(String[] args) {
        //  Time Stamp: 28th March 2K19, 01:00 PM..!!
        String urlFilePath = "I:\\0\\a.txt";

        ReadUrlFile obj = new ReadUrlFile(urlFilePath);
        ArrayList<String> urlList = obj.extractUrlList();

        System.out.println("\nURL List :=>");
        obj.displayUrlList(urlList);
    }

    public ArrayList<String> extractUrlList() {
        initializeUrlListUsingBuffer();
        return getUrlList();
    }

    private void initializeUrlListCBC() {
        //  initializeUrlListCBC => initializeUrlListCharacterByCharacter
        //  Reading from the urlFile one char. at a time which is quite slow process.
        File f = new File(urlFilePath);
        this.urlList = new ArrayList<>();
        if (f.isFile()) {
            try (FileReader fileReader = new FileReader(f)) {
                String singleLine = "";
                while (fileReader.ready()) {
                    char currentChar = (char) fileReader.read();
                    boolean hasNextChar = fileReader.ready();

                    //  When currentChar is a newLine char. & file hasNextChar to read.
                    if (currentChar == '\n' && hasNextChar) {
                        //  Append the singleLine.
                        urlList.add(singleLine);
                        singleLine = "";
                        continue;
                    }
                    //  When reached the end of file i.e. file doesn't hasNextChar to read.
                    else if (!hasNextChar) {
                        //  Append the currentChar into singleLine.
                        singleLine += currentChar;
                        urlList.add(singleLine);
                        break;
                    }
                    singleLine += currentChar;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initializeUrlListUsingBuffer() {
        //  Time Stamp: 28th March 2K19, 12:54 PM..!!
        File f = new File(urlFilePath);
        this.urlList = new ArrayList<>();
        if (f.isFile()) {
            String singleLine = "";
            char[] buffer = readFromFile(f);
            for (char c : buffer) {
                if (c == '\n') {
                    urlList.add(singleLine);
                    singleLine = "";
                    continue;
                }
                singleLine += c;
            }
            urlList.add(singleLine);
        }
    }

    private char[] readFromFile(File f) {
        int size = (int) f.length();
        char[] buffer = new char[size];

        try (FileReader fileReader = new FileReader(f)) {

            int bytesRead = fileReader.read(buffer);
            if (bytesRead != size) {
                System.out.println("File Not Read Completely...");
                System.exit(-110);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    void displayUrlList(ArrayList<String> urlList) {

        urlList.forEach(System.out::println);

    }

    public ArrayList<String> getUrlList() {
        return urlList;
    }
}
