package dev.koh.AdvancedJavaTraining.Java7NewFeatures.ManagingFilesAndDirectories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ReadAndWriteFile {

    public static void main(String[] args) {

        Path source = Paths.get("res/txtfiles/textDoc.txt");
        Path dir = Paths.get("res/txtfiles/a1");
        Path target = Paths.get("t1.txt");

        Charset charset = Charset.forName("UTF-8");
        ArrayList<String> content = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(source)) {

            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
                content.add(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = Files.newBufferedWriter(dir.resolve(target.getFileName()), charset)) {

            for (String s1 : content) {
                String s = s1.toString();
                bw.write(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
