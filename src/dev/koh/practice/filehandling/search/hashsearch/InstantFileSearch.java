package dev.koh.practice.filehandling.search.hashsearch;

import dev.koh.libs.utils.KohFilesUtil;
import dev.koh.libs.utils.MyTimer;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class InstantFileSearch {

    private FileIndexer fileIndexer;
    private Path sourcePath;
    private MyTimer myTimer;


    public static void main(String[] args) {

        InstantFileSearch obj = new InstantFileSearch();
        obj.start();

    }

    private void start() {

        System.out.println("Begin.");

        major();

        System.out.println("\nEnd.");

    }


    private void major() {

        setSourcePath(KohFilesUtil.userInputSourceDirPath());

        myTimer = new MyTimer();
        myTimer.startTimer();

        try {

            Path sourceDirPath = getSourcePath();

            fileIndexer = new FileIndexer();
            fileIndexer.scanSourcePath(sourceDirPath);
            fileIndexer.searchForFile();

        } catch (InvalidPathException e) {
            System.out.println(e.getMessage());
        }

        myTimer.stopTimer(true);

    }

    private Path getSourcePath() {
        return sourcePath;
    }

    private void setSourcePath(Path sourcePath) {
        this.sourcePath = sourcePath;
    }

}
