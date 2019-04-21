package dev.koh.libs.utils;

public class KohFilesUtil {

    public FileSize fileSize;
    public double updatedFileSize;

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

}
