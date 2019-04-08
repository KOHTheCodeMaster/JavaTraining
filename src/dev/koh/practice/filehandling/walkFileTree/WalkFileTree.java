package dev.koh.practice.filehandling.walkFileTree;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class WalkFileTree {

    private static long dirCount = 0;
    private static long fileCount = 0;
    private static double maxSize;
    private static String sizeUnit;
    private static Path maxSizedFile;


    public static void main(String[] args) throws IOException {

        WalkFileTree obj = new WalkFileTree();

        obj.major();
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

    private void major() throws IOException {
        MyFileVisitor myFileVisitor = new MyFileVisitor();

//        Path dir = Paths.get("F:\\UNSORTED");
        Path dir = Paths.get("F:\\HEADQUARTERS..!!\\CodeBase");

        Files.walkFileTree(dir, myFileVisitor);

        System.out.println("Dir. Count : " + dirCount);
        System.out.println("Files Count : " + fileCount);
        System.out.println("Largest File : " + maxSizedFile.toAbsolutePath());
        findSizeUnit();
        System.out.println("Max Size : " + maxSize + " " + sizeUnit);

    }

    public static class MyFileVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
//            System.out.println("About to visit Dir. : " + dir.toAbsolutePath());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//            System.out.println("Visiting File : " + file.toAbsolutePath());

            fileCount++;
            long a = file.toFile().length();
            if (maxSize < a) {
                maxSize = a;
                maxSizedFile = file;
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.out.println("\nFAILED to Visit File. : " + file.toAbsolutePath() + "\n");
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

            dirCount++;
            /*
                System.out.print(dirCount +" | ");

                if(dirCount % 1000 == 0) {
                    System.out.println();
                    System.out.println("Just visited Dir. : " + dir.toAbsolutePath());
                    System.out.println();
                }
            */
            return FileVisitResult.CONTINUE;
        }
    }
}
