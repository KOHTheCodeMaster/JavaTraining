package dev.koh.practice.Cryptography.Hash;

import dev.koh.libs.utils.KohFilesUtil;
import dev.koh.libs.utils.MyTimer;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {

    private Path sourcePath;
    private static Path currentFilePath;
    private String hash;
    private String algorithm;
    private MessageDigest messageDigest;
    private byte[] bytes;
//    private Scanner scanner;

    public HashGenerator(String algorithm) {

        this.algorithm = algorithm;
//        scanner = new Scanner(System.in);
//        currentFilePath = Paths.get(".");

        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        HashGenerator hashGenerator = new HashGenerator("SHA-256");
        hashGenerator.major();

    }

    private void major() {

        userInput();
        MyTimer myTimer = new MyTimer();
        myTimer.startTimer();

        try {

            Path rootDirPath = getSourcePath();
            HashFileVisitor hashFileVisitor = new HashFileVisitor();

            Files.walkFileTree(rootDirPath, hashFileVisitor);
//            displayStatus();

        } catch (InvalidPathException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        myTimer.stopTimer();
        System.out.println("Total Time Taken : " + myTimer.getTotalTimeTaken() + " " + myTimer.getTimeUnit());
    }

    public static void setCurrentFilePath(Path currentFilePath) {
        HashGenerator.currentFilePath = currentFilePath;
    }


    private void userInput() {

        System.out.println("Enter Source Path: ");
//        Path path = Paths.get(scanner.nextLine());
        Path path = Paths.get(new java.util.Scanner(System.in).nextLine());

        try {
            path.toRealPath(LinkOption.NOFOLLOW_LINKS);
            setSourcePath(path);
        } catch (InvalidPathException | IOException e) {
            e.printStackTrace();
        }

    }

    public String generateStringHash(String str) {

        this.bytes = messageDigest.digest(str.getBytes());
        StringBuilder hashString = bytesToHexString(this.bytes);
        this.hash = hashString + "";
        return this.hash;
    }

    private StringBuilder bytesToHexString(byte[] bytes) {

        StringBuilder result = new StringBuilder();

        for (byte b : bytes)
            result.append(String.format("%02x", b));

        return result;
    }

    private byte[] extractHashByteArray(Path sourceFilePath) {

//        long currentTimeMillis = System.currentTimeMillis();
        File file = sourceFilePath.toFile();

        //  Check Valid sourceFilePath
        try {
            if (!file.isFile() || !file.exists())
                throw new FileNotFoundException("File Not Found!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] byteBuff = new byte[1 << 15];

        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(byteBuff)) != -1)
                messageDigest.update(byteBuff, 0, bytesRead);

        } catch (IOException e) {
            e.printStackTrace();
        }

        bytes = messageDigest.digest();

//        long endTime = System.currentTimeMillis();
//        System.out.print("File Processed in Time : ");
//        System.out.println(endTime - currentTimeMillis + " ms");

        return bytes;
    }

    private StringBuilder bytesToHexString2(byte[] bytes) {
        //  Slow...
        StringBuilder result = new StringBuilder();

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

    private static Path getCurrentFilePath() {
        return currentFilePath;
    }

    private void displayHash() {
        KohFilesUtil kohFilesUtil = new KohFilesUtil();
        long fileLength = getCurrentFilePath().toFile().length();

        kohFilesUtil.updateFileSizeAndUnit(fileLength);
        String currentFile = "null";
        try {
            currentFile = getCurrentFilePath().toRealPath().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        displayByteArray(this.bytes);
        System.out.println("Input File : " + currentFile
                + "\nInput File Length : " + kohFilesUtil.getUpdatedFileSize()
                + " " + kohFilesUtil.getFileSize().getUnit()
                + "\nHash Length : " + hash.length()
                + " | Algorithm : " + algorithm
                + "\nHash : " + hash);
    }

    class HashFileVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

            //  Update currentFilePath with the currently visiting file.
            HashGenerator.setCurrentFilePath(file);

            bytes = extractHashByteArray(file);
            hash = bytesToHexString(bytes) + "";
            displayHash();

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.out.println("\nFAILED to Visit File. : " + file.toAbsolutePath() + "\n");
            System.out.println(exc.getMessage());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {

//            trackDir(dir);

            return FileVisitResult.CONTINUE;
        }

/*
        private void trackDir(Path dir) {

            if (dirCount % 20 == 0) {
                System.out.print(dirCount + " | ");
            }

            if (dirCount % 500 == 0) {
                System.out.println();
                displayStatus();
                System.out.println();
            }

            if (fileCount % 5000 == 0) {
                System.out.println();
                System.out.println("Just visited Dir. : " + dir.toAbsolutePath());

                displayStatus();

                System.out.println();
            }
        }
*/

    }

}