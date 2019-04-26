package dev.koh.practice.filehandling.walkFileTree;

import dev.koh.libs.utils.KohFilesUtil;
import dev.koh.libs.utils.MyTimer;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectorySizeFinder {

    private long dirCount;
    private long fileCount;
    private double maxSize;
    private double dirSize;
    private Path maxSizedFile;
    private KohFilesUtil kohFilesUtil;

    public static void main(String[] args) {

        DirectorySizeFinder directorySizeFinder = new DirectorySizeFinder();
        directorySizeFinder.major();

    }

    private void displayStatus() {

        System.out.println("Dir. Count : " + dirCount);
        System.out.println("Files Count : " + fileCount);

        if (fileCount > 0) {

            System.out.println("Largest File : " + maxSizedFile.toAbsolutePath() + "");

            kohFilesUtil.updateFileSizeAndUnit(maxSize);
            double tempMaxSize = kohFilesUtil.getUpdatedFileSize();
            System.out.println("Max Size : " + tempMaxSize + " " + kohFilesUtil.getFileSize().getUnit());
        }

        kohFilesUtil.updateFileSizeAndUnit(dirSize);
        double tempDirSize = kohFilesUtil.getUpdatedFileSize();
        System.out.println("Dir. Size : " + tempDirSize + " " + kohFilesUtil.getFileSize().getUnit());

    }

    private void major() {

//        String rootDir = "F:/";
//        String rootDir = "E:\\My Drive";

        System.out.println("Enter Root Dir. Path: ");
        String rootDir = new java.util.Scanner(System.in).nextLine();

        kohFilesUtil = new KohFilesUtil();
        MyTimer myTimer = new MyTimer();
        myTimer.startTimer();

        try {

            Path rootDirPath = Paths.get(rootDir);
            DirectorySizeVisitor directorySizeVisitor = new DirectorySizeVisitor();

            Files.walkFileTree(rootDirPath, directorySizeVisitor);
            displayStatus();

        } catch (InvalidPathException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        myTimer.stopTimer();

        System.out.println("Time Taken: " + myTimer.getTotalTimeTaken() + " " + myTimer.getTimeUnit());

    }

    public class DirectorySizeVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

            //  Increment the filesCount by 1
            fileCount++;

            //  Update dirSize by adding the currentFileSize
            double currentFileSize = attrs.size();
            dirSize += currentFileSize;

            //  Update maxSize & maxSizedFile
            if (maxSize < currentFileSize) {
                maxSize = currentFileSize;
                maxSizedFile = file;
            }

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

            dirCount++;
            trackDir(dir);

            return FileVisitResult.CONTINUE;
        }

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

    }
}
