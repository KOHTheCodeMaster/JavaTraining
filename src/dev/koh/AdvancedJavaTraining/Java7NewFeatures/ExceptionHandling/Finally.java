package dev.koh.AdvancedJavaTraining.Java7NewFeatures.ExceptionHandling;

import java.io.*;

public class Finally {

    private FileReader fileReader = null;
    private FileWriter fileWriter = null;
    private BufferedReader bufferedReader = null;

    void handleExceptionsUsingFinally() {

        try {
            fileReader = new FileReader("./res/txtfiles/testDoc.txt");
            fileWriter = new FileWriter("./res/txtfiles/textDoc.txt");
            bufferedReader = new BufferedReader(fileReader);
            String temp;

            while ((temp = bufferedReader.readLine()) != null) {
                fileWriter.write(temp);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null)
                    fileWriter.close();
                if (fileReader != null)
                    fileReader.close();
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    void usingTryWithResources() {
        try (
                FileReader fileReader = new FileReader("./res/txtfiles/testDoc.txt");
                FileWriter fileWriter = new FileWriter("./res/txtfiles/textDoc.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

        ) {
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                fileWriter.write(temp);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
