package dev.koh.libs.utils;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class KohFilesUtil {

    private FileSize fileSize;
    private double updatedFileSize;

    public void updateFileSizeAndUnit(double fileSize) {

        this.updatedFileSize = fileSize;

        if (this.updatedFileSize < FileSize.KILO_BYTE.getSizeInBytes()) this.fileSize = FileSize.BYTE;
        else if (this.updatedFileSize < FileSize.MEGA_BYTE.getSizeInBytes()) this.fileSize = FileSize.KILO_BYTE;
        else if (this.updatedFileSize < FileSize.GIGA_BYTE.getSizeInBytes()) this.fileSize = FileSize.MEGA_BYTE;
        else if (this.updatedFileSize < FileSize.TERA_BYTE.getSizeInBytes()) this.fileSize = FileSize.GIGA_BYTE;
        else if (this.updatedFileSize < FileSize.PETA_BYTE.getSizeInBytes()) this.fileSize = FileSize.TERA_BYTE;
        else if (this.updatedFileSize < FileSize.EXA_BYTE.getSizeInBytes()) this.fileSize = FileSize.PETA_BYTE;
        else if (this.updatedFileSize < FileSize.ZETA_BYTE.getSizeInBytes()) this.fileSize = FileSize.EXA_BYTE;
        else if (this.updatedFileSize < FileSize.YOTTA_BYTE.getSizeInBytes()) this.fileSize = FileSize.ZETA_BYTE;

        this.updatedFileSize /= this.fileSize.getSizeInBytes();

    }

    public static Path userInputSourceDirPath() {

        System.out.println("Enter Source Dir. Path: ");
        Path path = null;
        while (path == null) {
            try {
                path = Paths.get(new java.util.Scanner(System.in).nextLine());
                path.toRealPath(LinkOption.NOFOLLOW_LINKS);
            } catch (InvalidPathException | IOException | NullPointerException e) {
                e.getMessage();
                System.out.println("Please Enter a Valid Path!");
                path = null;
            }
        }
        return (path);

    }

    public FileSize getFileSize() {
        return fileSize;
    }

    public double getUpdatedFileSize() {
        return updatedFileSize;
    }
}
