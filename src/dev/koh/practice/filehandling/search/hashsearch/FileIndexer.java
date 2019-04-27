package dev.koh.practice.filehandling.search.hashsearch;

import dev.koh.libs.utils.KohFilesUtil;
import dev.koh.practice.Cryptography.Hash.HashGenerator;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FileIndexer implements Serializable {

    private Map<String, LinkedList<MyFile>> memoryMap;
    private LinkedList<MyFile> linkedList;

    private transient Path sourceDirPath;

    private double filesCount = 0;
    private double dirsCount = 0;
    private double failedToIndexFilesCount;

    FileIndexer() {

        memoryMap = new HashMap<>();
        linkedList = new LinkedList<>();

    }

    void scanSourcePath(Path path) {

        setSourceDirPath(path);

        try {

            IndexFileVisitor indexFileVisitor = new IndexFileVisitor();
            Files.walkFileTree(getSourceDirPath(), indexFileVisitor);

            System.out.println("\nFiles Count : " + indexFileVisitor.getFilesCount());
            System.out.println("Dirs. Count : " + indexFileVisitor.getDirsCount());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void searchForFile() {

        System.out.println("Enter File Name to Search For: ");

        InstantFileSearch.myTimer.pauseTimer();
        String fileNameToSearch = new java.util.Scanner(System.in).nextLine();
        InstantFileSearch.myTimer.continueTimer();

        String currentFileNameHash = generateFileNameHash(fileNameToSearch);
        linkedList = memoryMap.get(currentFileNameHash);

        if (linkedList == null)
            System.out.println("File Not Found!");
        else {
            int i = 0;
            while (i < linkedList.size()) {
                System.out.println("----------------\n" +
                        "File Found..!!\n" +
                        "----------------");
                System.out.println(linkedList.get(i));
                i++;
            }
        }
    }

    private String generateFileNameHash(String fileName) {
        HashGenerator hashGenerator = new HashGenerator("SHA-256");
        return hashGenerator.generateStringHash(fileName);
    }

    private String generateFileNameHash(MyFile myFile) {
        HashGenerator hashGenerator = new HashGenerator("SHA-256");
        return hashGenerator.generateStringHash(myFile.getFileName());
    }

    private Path getSourceDirPath() {
        return sourceDirPath;
    }

    private void setSourceDirPath(Path sourceDirPath) {
        this.sourceDirPath = sourceDirPath;
    }

    public double getFilesCount() {
        return filesCount;
    }

    public double getDirsCount() {
        return dirsCount;
    }

    private class IndexFileVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

            filesCount++;

            MyFile myFile = createMyFile(file, attrs);

            if (myFile == null) {
                System.out.println("Failed to Index File : " + file.toAbsolutePath());
                return FileVisitResult.CONTINUE;
            }

//            System.out.println("FN: " + myFile.getFileName());
            indexCurrentFile(myFile);

            return FileVisitResult.CONTINUE;

        }

        private void indexCurrentFile(MyFile myFile) {

            String fileNameHash = generateFileNameHash(myFile);

            LinkedList<MyFile> tempLinkedList = new LinkedList<>();
            tempLinkedList.add(myFile);

            if (memoryMap.get(fileNameHash) == null)
                memoryMap.put(fileNameHash, tempLinkedList);
            else {
                linkedList = memoryMap.get(fileNameHash);
                linkedList.addLast(myFile);
            }

        }

/*
        private String generateFileNameHash(MyFile myFile) {
            HashGenerator hashGenerator = new HashGenerator("SHA-256");
            return hashGenerator.generateStringHash(myFile.getFileName().toString());
        }
*/

        private double parseHexStringToDecimal(String hex) {
            String digits = "0123456789ABCDEF";
            hex = hex.toUpperCase();
            double val = 0;
            for (int i = 0; i < hex.length(); i++) {
                char c = hex.charAt(i);
                int d = digits.indexOf(c);
                val = 16 * val + d;
            }
            return val;
        }

        private MyFile createMyFile(Path path, BasicFileAttributes attrs) {

            File file;
            String fileName;
            String absolutePath;
            String canonicalPath;
            double fileSize;
            String fileSizeUnit;

            try {

                file = path.toFile().getCanonicalFile();
                fileName = file.getName();
                absolutePath = file.getAbsolutePath();
                canonicalPath = file.getCanonicalPath();
                fileSize = attrs.size();

                KohFilesUtil kohFilesUtil = new KohFilesUtil();
                kohFilesUtil.updateFileSizeAndUnit(fileSize);
                fileSize = kohFilesUtil.getUpdatedFileSize();
                fileSizeUnit = kohFilesUtil.getFileSize().getUnit();

                return new MyFile(file, fileName, absolutePath, canonicalPath, fileSize, fileSizeUnit);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("I/O Exception while indexing the File : " + path.toAbsolutePath());
            }

            return null;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {

            failedToIndexFilesCount++;
            System.out.println("\nFAILED to Visit File. : " + file.toAbsolutePath() + "\n");
            System.out.println(exc.getMessage());
            return FileVisitResult.CONTINUE;

        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            dirsCount++;
            return FileVisitResult.CONTINUE;
        }

        double getFilesCount() {
            return filesCount;
        }

        double getDirsCount() {
            return dirsCount;
        }
    }


}
