package dev.koh.practice.filehandling.search.hashsearch;

import dev.koh.libs.utils.KohFilesUtil;
import dev.koh.practice.Cryptography.Hash.HashGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FileIndexer {

    private Map<String, LinkedList<MyFile>> memoryMap;
    private LinkedList<MyFile> linkedList;
    private Path sourceDirPath;
//    Double aDouble;

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

//            f1();
//            searchForFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void f1() {
        double d = 0;
        System.out.println("Double Max: + " + Double.MAX_VALUE);
        System.out.println("Long Max: + " + Long.MAX_VALUE);

        for (d = 0; d < Double.MAX_VALUE; d++) {
            memoryMap.put(String.valueOf(d), null);

            if (d % 1E5 == 0) {
                System.out.print(" d : " + d + " | ");
//                System.out.println(memoryMap.get(String.valueOf(d)));
            }

        }
        System.out.println("..!!");

    }

    void searchForFile() {

        Path filePath = Paths.get(new java.util.Scanner(System.in).nextLine());
        String currentFileNameHash = generateFileNameHash(filePath.getFileName().toString());

        linkedList = memoryMap.get(currentFileNameHash);
        if (linkedList == null)
            System.out.println("File Not Found!");
        else {
            System.out.println("\n----------------\n" +
                    "File Found..!!" +
                    "\n----------------\n");
            int i = 0;
            while (i < linkedList.size()) {
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

    private class IndexFileVisitor extends SimpleFileVisitor<Path> {

        private double filesCount = 0;
        private double dirsCount = 0;


        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

            filesCount++;

            MyFile myFile = createMyFile(file, attrs);

            if (myFile == null) {
                System.out.println("Failed to Index File : " + file.toAbsolutePath());
                return FileVisitResult.CONTINUE;
            }

            String fileNameHash = generateFileNameHash(myFile);

            LinkedList<MyFile> tempLinkedList = new LinkedList<>();
            tempLinkedList.add(myFile);

            if (memoryMap.get(fileNameHash) == null)
                memoryMap.put(fileNameHash, tempLinkedList);
            else {
//                linkedList = memoryMap.get(fileNameHash);
            }
            return FileVisitResult.CONTINUE;

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

                //                System.out.println(myFile);
                return new MyFile(file, fileName, absolutePath, canonicalPath, fileSize, fileSizeUnit);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("I/O Exception while indexing the File : " + path.toAbsolutePath());
            }

            return null;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
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
