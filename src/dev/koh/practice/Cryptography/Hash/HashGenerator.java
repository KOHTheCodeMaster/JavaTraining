package dev.koh.practice.Cryptography.Hash;

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
//        algorithm = "MD5";
        algorithm = "SHA-512";
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
        byte[] bytes = extractByteArray();
        hash = bytesToHexString2(bytes) + "";
        System.out.println(hash);
        displayHash();
    }

    private void displayHash() {
        System.out.println("Input Length : " + getSourcePath().getFileName().toString().length()
                + "\nInput Data : " + getSourcePath().getFileName().toString()
                + "\nHash Length : " + hash.length()
                + " | Algorithm : " + algorithm
                + "\nHash : " + hash);
    }

    private byte[] extractByteArray() {

        String rootPath = getSourcePath().getFileName() + "";

        return messageDigest.digest(rootPath.getBytes());

    }

    private void userInput() {

        System.out.println("Enter Root Dir. Path: ");
        setSourcePath(Paths.get(scanner.nextLine()));


    }

    private StringBuilder bytesToHexString(byte[] bytes) {

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

    private StringBuilder bytesToHexString2(byte[] bytes) {

        StringBuilder result = new StringBuilder();
        displayByteArray(bytes);

        for (byte b : bytes)
            result.append(String.format("%02x", b));

        return result;
    }

    private Path getSourcePath() {
        return sourcePath;
    }

    private void setSourcePath(Path sourcePath) {
        this.sourcePath = sourcePath;
    }

}
