package dev.koh.AdvancedJavaTraining.Java7NewFeatures.ManagingFilesAndDirectories;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UsingThePathClass {

    public static void main(String[] args) throws IOException {

        Path source = Paths.get("res/txtfiles/textDoc.txt");

        System.out.println("File Name: " + source.getFileName());
        System.out.println("File Name: " + source.toString());

        System.out.println("Name Count (intermediate dirs) : " + source.getNameCount());

        /*
            No Negative args. allowed!
            source.getName(0)  => top-level dir. of the relative/absolute path provided to Paths.get(...)
            System.out.println(source.getNameCount());  => 3
            System.out.println(source.getName(2));  => file name, as indexing of getName starts from 0
         */

        System.out.println(source.getName(source.getNameCount() - 1));

        System.out.println(source.toRealPath(LinkOption.NOFOLLOW_LINKS));

    }

}
