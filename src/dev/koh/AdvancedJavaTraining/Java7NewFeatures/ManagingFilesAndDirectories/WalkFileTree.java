package dev.koh.AdvancedJavaTraining.Java7NewFeatures.ManagingFilesAndDirectories;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class WalkFileTree {

    public static void main(String[] args) throws IOException {

        MyFileVisitor myFileVisitor = new MyFileVisitor();
        Path dir = Paths.get("F:\\");

        Files.walkFileTree(dir, myFileVisitor);

    }

    public static class MyFileVisitor extends SimpleFileVisitor<Path> {


        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.println("About to visit Dir. : " + dir.toAbsolutePath());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println("Visiting File : " + file.toAbsolutePath());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.out.println("\nFAILED to Visit File. : " + file.toAbsolutePath() + "\n");
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            System.out.println("Just visited Dir. : " + dir.toAbsolutePath());
            return FileVisitResult.CONTINUE;
        }
    }
}
