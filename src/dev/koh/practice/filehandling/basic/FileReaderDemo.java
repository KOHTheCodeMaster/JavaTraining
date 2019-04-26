package dev.koh.practice.filehandling.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {

    public static void main(String[] args) {

//        demonstrateFileReader("F:\\HEADQUARTERS..!!\\CodeBase\\Java\\CoreJava" +
//                "\\JavaTraining\\res\\txtfiles\\testDoc.txt");

        demonstrateFileReader2("F:\\HEADQUARTERS..!!\\CodeBase\\Java\\CoreJava" +
                "\\JavaTraining\\res\\txtfiles\\testDoc.txt");

    }

    private static void demonstrateFileReader(String filePath) {

        try (FileReader fr = new FileReader(filePath)) {

            int c = fr.read();

            while (fr.ready()) {
                System.out.print((char) c);
                c = fr.read();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void demonstrateFileReader2(String filePath) {

        try (FileReader fr = new FileReader(filePath)) {

            int len = (int) new File(filePath).length();
            char[] charArray = new char[len];
            int numOfCharRead = fr.read(charArray);

            System.out.println("File Loaded: " + filePath);
            System.out.println("Number Of Characters Read: " + numOfCharRead);
            for (char c : charArray)
                System.out.print(c);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

/*
 *  Time Stamp: 23rd March 2K19, 07:50 PM..!!
 *  Java I/O Package.
 *  Self-Practice.
 *
 *  Latest Update:
 *  1. InputStreamReader
 *  2. FileReader
 *
 *  Change Log:
 *  3rd Commit:
 *  System.in.read() method.
 *
 *  Code Developed By,
 *  ~K.O.H..!! ^__^
 */