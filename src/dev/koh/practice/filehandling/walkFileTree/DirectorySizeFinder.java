package dev.koh.practice.filehandling.walkFileTree;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectorySizeFinder {

    private static long dirCount = 0;
    private static long fileCount = 0;
    private static double maxSize;
    private static double dirSize;
    private static double tempDirSize;
    private static double tempMaxSize;
    private static String sizeUnit;
    private static Path maxSizedFile = null;

    public static void main(String[] args) {

        DirectorySizeFinder directorySizeFinder = new DirectorySizeFinder();
        directorySizeFinder.major();

    }

    private static void displayStatus() {

        System.out.println("Dir. Count : " + dirCount);
        System.out.println("Files Count : " + fileCount);

        if (fileCount > 0) {
            String largestFilePath = maxSizedFile.toAbsolutePath() + "";
            System.out.println("Largest File : " + largestFilePath);

            KohFilesUtil.findSizeUnit(maxSize, 1);
            System.out.println("Max Size : " + tempMaxSize + " " + sizeUnit);
        }
        KohFilesUtil.findSizeUnit(dirSize, 2);
        System.out.println("Dir. Size : " + tempDirSize + " " + sizeUnit);

    }

    static String getSizeUnit() {
        return sizeUnit;
    }

    static void setSizeUnit(String sizeUnit) {
        DirectorySizeFinder.sizeUnit = sizeUnit;
    }

    static void setTempMaxSize(double tempMaxSize) {
        DirectorySizeFinder.tempMaxSize = tempMaxSize;
    }

    //  ========================== Getters & Setters =================================

    public static void setTempDirSize(double tempDirSize) {
        DirectorySizeFinder.tempDirSize = tempDirSize;
    }

    private void major() {

//        String rootDir = "F:/";
//        String rootDir = "E:\\My Drive";

        System.out.println("Enter Root Dir. Path: ");
        String rootDir = new java.util.Scanner(System.in).nextLine();

        MyTimer.startTimer();

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

        MyTimer.endTimer();
        MyTimer.findTimeUnit();

        System.out.println("Time Taken: " + MyTimer.totalTimeTaken + " " + MyTimer.timeUnit);

    }

    public static class MyTimer {
        static double currentTime;
        static double endTime;
        static double totalTimeTaken;
        static String timeUnit;

        static void startTimer() {
            currentTime = System.currentTimeMillis();
        }

        static void endTimer() {
            endTime = System.currentTimeMillis();
            totalTimeTaken = endTime - currentTime;
        }

        static void findTimeUnit() {

            final int THOUSAND_MILLI_SECONDS = 1000;

            if (totalTimeTaken < THOUSAND_MILLI_SECONDS) {
                timeUnit = "ms";
            } else if (totalTimeTaken < 60 * THOUSAND_MILLI_SECONDS) {
                timeUnit = "s";
                totalTimeTaken /= THOUSAND_MILLI_SECONDS;
            } else if (totalTimeTaken < 60 * 60 * THOUSAND_MILLI_SECONDS) {
                timeUnit = "minutes";
                totalTimeTaken /= 60 * THOUSAND_MILLI_SECONDS;
            }/* else if (totalTimeTaken < TERA) {
                timeUnit = "GB";
                totalTimeTaken /= GIGA;
            } else if (totalTimeTaken < PETA) {
                timeUnit = "TB";
                totalTimeTaken /= TERA;
            }*/

        }

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
