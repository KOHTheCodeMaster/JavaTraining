package dev.koh.practice.filehandling.walkFileTree;

class KohFilesUtil {

    static void findSizeUnit(double size, int flag) {

        double updatedSize = size;
        String sizeUnit = DirectorySizeFinder.getSizeUnit();

        final int KILO = 1000;
        final int MEGA = 1_000_000;
        final int GIGA = 1_000_000_000;
        final double TERA = 1_000_000_000_000d;
        final double PETA = 1_000_000_000_000_000d;

        if (updatedSize < KILO) {
            sizeUnit = "B";
        } else if (updatedSize < MEGA) {
            sizeUnit = "KB";
            updatedSize /= KILO;
        } else if (updatedSize < GIGA) {
            sizeUnit = "MB";
            updatedSize /= MEGA;
        } else if (updatedSize < TERA) {
            sizeUnit = "GB";
            updatedSize /= GIGA;
        } else if (updatedSize < PETA) {
            sizeUnit = "TB";
            updatedSize /= TERA;
        }

        if (flag == 1)
            DirectorySizeFinder.setTempMaxSize(updatedSize);
        else
            DirectorySizeFinder.setTempDirSize(updatedSize);

        DirectorySizeFinder.setSizeUnit(sizeUnit);
    }

}
