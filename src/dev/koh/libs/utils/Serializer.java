package dev.koh.libs.utils;

import dev.koh.practice.filehandling.search.hashsearch.FileIndexer;

import java.io.*;

public class Serializer implements Serializable {

    private Serializable object;
    private File serializeFile;

    public Serializer(Serializable object, File serializeFile) {
        this.object = object;
        this.serializeFile = serializeFile;
    }

    public boolean serializeObject() {

        try (FileOutputStream fileOutputStream = new FileOutputStream(serializeFile);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(this.object);

        } catch (IOException e) {
            System.out.println("Could not Save the Object!");
            e.getMessage();
            return false;
        }

        return true;

    }

    public Serializable deserializeObject() {

        try (FileInputStream fileInputStream = new FileInputStream(serializeFile);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            this.object = (Serializable) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not Read the Object!");
            e.getMessage();
            return null;
        }
        return this.object;
    }

    public boolean checkForExistingSerialization() {
        return getSerializeFile().exists();
    }

    public Object getObject() {
        return object;
    }

    public void setObject(FileIndexer object) {
        this.object = object;
    }

    public File getSerializeFile() {
        return serializeFile;
    }

    public void setSerializeFile(File serializeFile) {
        this.serializeFile = serializeFile;
    }
}
