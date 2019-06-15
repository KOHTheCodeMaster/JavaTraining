package dev.koh.practice.filehandling.search.hashsearch;

import dev.koh.libs.utils.KohFilesUtil;
import dev.koh.libs.utils.MyTimer;
import dev.koh.libs.utils.Serializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class InstantFileSearch {

    private FileIndexer fileIndexer;
    private Path sourcePath;
    static MyTimer myTimer;
    private static Serializer serializer;
    private File serializationFile;
    private static String serializationFileRelativeLocation;

    static {
        serializationFileRelativeLocation = "res/serialization/index/fileIndexer.ser";
    }


    public static void main(String[] args) {

        InstantFileSearch obj = new InstantFileSearch();
        obj.start();

    }

    private void start() {

        InstantFileSearch.myTimer = new MyTimer();
        InstantFileSearch.myTimer.startTimer();

        System.out.println("Begin.");

        major();

        System.out.println("\nEnd.");

    }


    private void major() {

        InstantFileSearch.myTimer.pauseTimer();
        setSourcePath(KohFilesUtil.userInputSourceDirPath());
        InstantFileSearch.myTimer.continueTimer();

        try {

            Path sourceDirPath = getSourcePath();
            serializationFile = new File(serializationFileRelativeLocation);

            InstantFileSearch.serializer = new Serializer(fileIndexer, serializationFile);

            //  Check for existence of serializationFile
            boolean isSerializationFileExists = serializationFile.exists();
            if (!isSerializationFileExists) {

                initializeSerializationFile();

                System.out.println("Initializing Indexer...");
                fileIndexer = new FileIndexer();

                System.out.println("Scanning Source Dir.");
                fileIndexer.scanSourcePath(sourceDirPath);

                //  Save the current State of the fileIndexer
                serialization();

            } else {
                System.out.println("Indexer Loaded!");
                deserialization();
//                System.out.println(fileIndexer);
            }

            fileIndexer.searchForFile();



        } catch (InvalidPathException e) {
            System.out.println(e.getMessage());
        }

        InstantFileSearch.myTimer.stopTimer(true);

    }

    private void initializeSerializationFile() {
        if (!serializationFile.exists()) {
            try {
                serializationFile.createNewFile();
            } catch (IOException e) {
                e.getMessage();
                System.out.println("Could not ");
            }
        }
    }

    private void deserialization() {

        //  Update the serializer with the fileIndexer object state to be stored & serialized
        InstantFileSearch.serializer.setObject(fileIndexer);
        InstantFileSearch.serializer.setSerializeFile(serializationFile);

        //  Serialize the fileIndexer object
        fileIndexer = (FileIndexer) InstantFileSearch.serializer.deserializeObject();

//        if (!serializeSuccessful)
//            System.out.println("Failed to Serialize fileIndexer!");
/*
        else
            System.out.println("fileIndexer Serialized successfully @: "
                    + serializer.getSerializeFile().getAbsolutePath());
*/
    }

    private void serialization() {

        //  Update the serializer with the fileIndexer object state to be stored & serialized
        InstantFileSearch.serializer.setObject(fileIndexer);
        InstantFileSearch.serializer.setSerializeFile(serializationFile);

        //  Serialize the fileIndexer object
        boolean serializeSuccessful = InstantFileSearch.serializer.serializeObject();

        if (!serializeSuccessful)
            System.out.println("Failed to Serialize fileIndexer!");
/*
        else
            System.out.println("fileIndexer Serialized successfully @: "
                    + serializer.getSerializeFile().getAbsolutePath());
*/
    }

    private boolean checkSerializationFileExistence() {

        boolean indexerExists = InstantFileSearch.serializer.checkForExistingSerialization();
//        if (indexerExists)
//            fileIndexer = (FileIndexer) serializer.deserializeObject();

        return indexerExists;
    }

    private Path getSourcePath() {
        return sourcePath;
    }

    private void setSourcePath(Path sourcePath) {
        this.sourcePath = sourcePath;
    }

}
