package dev.koh.AdvancedJavaTraining.Java7NewFeatures.ManagingFilesAndDirectories;

import java.io.IOException;
import java.nio.file.*;

public class BasicFileOperatios {

    public static void main(String[] args) throws IOException {

        Path source = Paths.get("res/txtfiles/textDoc.txt");
        Path dir = Paths.get("res/txtfiles/a1");
        Path target = Paths.get("res/txtfiles/t1.txt");

        try {
            Files.createDirectory(dir);
        } catch (FileAlreadyExistsException e) {
            e.getMessage();
        }
//        Files.delete(target);

        Files.copy(source, dir.resolve(target.getFileName()), StandardCopyOption.REPLACE_EXISTING);
//        Files.move(source, dir.resolve(target.getFileName()), StandardCopyOption.REPLACE_EXISTING);

    }

}
