package dev.koh.practice.Cryptography.Hash;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class HashGenerator {

    private Path sourcePath;
    private String hash;
    private String algorithm;
    private MessageDigest messageDigest;
    private Scanner scanner;

    public HashGenerator() {

        scanner = new Scanner(System.in);
        algorithm = "SHA-256";

        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        HashGenerator hashGenerator = new HashGenerator();
        hashGenerator.major();

    }

    private void major() {

        userInput();
        long currentTime = System.currentTimeMillis();
        byte[] bytes = extractHashByteArray();

        hash = bytesToHexString(bytes) + "";
        System.out.println(hash);
        displayHash();

        long endTime = System.currentTimeMillis();
        System.out.println("Total Time Taken : " + (endTime - currentTime) + " ms");
    }

    private void userInput() {

        System.out.println("Enter Source Path: ");
        Path path = Paths.get(scanner.nextLine());

        try {
            path.toRealPath(LinkOption.NOFOLLOW_LINKS);
            setSourcePath(path);
        } catch (InvalidPathException | IOException e) {
            e.printStackTrace();
        }

    }

    private byte[] extractHashByteArray() {

        String rootPath = getSourcePath().toString();
        byte[] dataBytes = extractByteData(rootPath);

        return messageDigest.digest(dataBytes);

    }

    private byte[] extractByteData(String sourcePath) {

        long currentTimeMillis = System.currentTimeMillis();
        File file = new File(sourcePath);
        byte[] bytes = new byte[(int) file.length()];

        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

            int i = bufferedInputStream.read(bytes);
            System.out.println("Bytes Read : " + i);

        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.print("File Read in Time : ");
        System.out.println(endTime - currentTimeMillis + " ms");

        return bytes;
    }

    private StringBuilder bytesToHexString(byte[] bytes) {

        StringBuilder result = new StringBuilder();
        displayByteArray(bytes);

        for (byte b : bytes)
            result.append(String.format("%02x", b));

        return result;
    }

    private void displayHash() {
        System.out.println("Input Length : " + getSourcePath().getFileName().toString().length()
                + "\nInput Data : " + getSourcePath().getFileName().toString()
                + "\nHash Length : " + hash.length()
                + " | Algorithm : " + algorithm
                + "\nHash : " + hash);
    }

    private StringBuilder bytesToHexString2(byte[] bytes) {
        //  Slow...
        StringBuilder result = new StringBuilder();

        displayByteArray(bytes);

        for (byte b : bytes) {
            String temp = Integer.toHexString(b & 0xff);
            if (temp.length() < 2)
                result.append("0");

            result.append(temp);
            System.out.println("result : " + result);
        }

        return result;
    }

    private void displayByteArray(byte[] bytes) {
        System.out.print("Bytes : ");

        for (byte b : bytes)
            System.out.print(b + " ");

        System.out.println();
    }

    private Path getSourcePath() {
        return sourcePath;
    }

    private void setSourcePath(Path sourcePath) {
        this.sourcePath = sourcePath;
    }

}
