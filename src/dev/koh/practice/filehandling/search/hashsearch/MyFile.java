package dev.koh.practice.filehandling.search.hashsearch;

import java.io.File;
import java.io.Serializable;

public class MyFile implements Serializable {

    private File file;
    private String fileName;
    private String absolutePath;
    private String canonicalPath;
    private String fileSizeUnit;
    private double fileSize;


    public MyFile(File file, String fileName, String absolutePath,
                  String canonicalPath, double fileSize, String fileSizeUnit) {
        this.file = file;
        this.fileName = fileName;
        this.absolutePath = absolutePath;
        this.canonicalPath = canonicalPath;
        this.fileSize = fileSize;
        this.fileSizeUnit = fileSizeUnit;

    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("File : ").append(file).append("\n");
        stringBuilder.append("File Name : ").append(fileName).append("\n");
        stringBuilder.append("Absolute Path : ").append(absolutePath).append("\n");
        stringBuilder.append("Canonical Path: ").append(canonicalPath).append("\n");
        stringBuilder.append("File Size: ").append(fileSize).append("\n");
        stringBuilder.append("File Size Unit: ").append(fileSizeUnit).append("\n");

        return stringBuilder.toString();
    }

    public String getFileName() {
        return fileName;
    }

}
