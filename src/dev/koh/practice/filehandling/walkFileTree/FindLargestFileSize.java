package dev.koh.practice.filehandling.walkFileTree;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FindLargestFileSize {

    private static long dirCount = 0;
    private static long fileCount = 0;
    private static double maxSize;
    private static String sizeUnit;
    private static Path maxSizedFile;

    public static void main(String[] args) throws IOException {

        FindLargestFileSize obj = new FindLargestFileSize();

        obj.major();

    }

    private void major() throws IOException {

        //  Custom SimpleFileVisitor
        MaxFileSizeFinder maxFileSizeFinder = new MaxFileSizeFinder();

//        Path dir = Paths.get("F:\\UNSORTED");
//        Path dir = Paths.get("F:\\HEADQUARTERS..!!\\CodeBase");
        Path dir = Paths.get(".");

        Files.walkFileTree(dir, maxFileSizeFinder);

        System.out.println("Dir. Count : " + dirCount);
        System.out.println("Files Count : " + fileCount);
        if (fileCount > 0)
            System.out.println("Largest File : " + maxSizedFile.toAbsolutePath());
        findSizeUnit();
        System.out.println("Max Size : " + maxSize + " " + sizeUnit);

    }

    private static void findSizeUnit() {

        final int KILO = 1000;
        final int MEGA = 1_000_000;
        final int GIGA = 1_000_000_000;
        final double TERA = 1_000_000_000_000d;
        final double PETA = 1_000_000_000_000_000d;

        if (maxSize < KILO) {
            sizeUnit = "B";
        } else if (maxSize < MEGA) {
            sizeUnit = "KB";
            maxSize /= KILO;
        } else if (maxSize < GIGA) {
            sizeUnit = "MB";
            maxSize /= MEGA;
        } else if (maxSize < TERA) {
            sizeUnit = "GB";
            maxSize /= GIGA;
        } else if (maxSize < PETA) {
            sizeUnit = "TB";
            maxSize /= TERA;
        }
    }

    public static class MaxFileSizeFinder extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            fileCount++;
//            double a = file.toFile().length();
            double a = attrs.size();
            if (maxSize < a) {
                maxSize = a;
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
            return FileVisitResult.CONTINUE;
        }
    }
}
