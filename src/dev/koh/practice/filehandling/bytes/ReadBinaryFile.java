package dev.koh.practice.filehandling.bytes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class ReadBinaryFile {

    public static void main(String[] args) {

        long currentTime = System.currentTimeMillis();

        ReadBinaryFile readBinaryFile = new ReadBinaryFile();
        readBinaryFile.major();

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - currentTime + "ms");

    }

    private void major() {

        String sourceDir = "F:\\Movies\\Halloween (2018) [WEBRip] [1080p] [YTS.AM]\\Halloween.2018.1080p.WEBRip.x264-[YTS.AM].mp4";

        byte[] bytes = extractByteData((sourceDir));                          //  3519ms
//        byte[] bytes = extractByteDataForSmallFiles(Paths.get(sourceDir));  //  4046ms

        System.out.println("Len: " + bytes.length);

//        String destinationPath = (sourceDir);
//        String targetFileName = "test.png";
//        writeBytes(bytes, targetFileName);

    }

    private byte[] extractByteData(String sourcePath) {

        //  Reads only INTEGER.MAX_VALUE size of Bytes cuz Array size can't exceed that limit.
        File file = new File(sourcePath);
        byte[] bytes = new byte[(int) file.length()];

        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

            int i = bufferedInputStream.read(bytes);
            System.out.println("Bytes Read : " + i);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    private byte[] extractByteDataForSmallFiles(Path sourcePath) {

        byte[] bytes = new byte[0];

        try {
            bytes = Files.readAllBytes(sourcePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    private byte[] extractByteData2(String sourcePath) {

        //  Super Slow!!!

        File file = new File(sourcePath);
        LinkedList<Byte> byteArrayList = new LinkedList<>();

        try (FileInputStream fileInputStream = new FileInputStream(file);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

            int i;
            while ((i = bufferedInputStream.read()) != -1)
                byteArrayList.add((byte) i);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Byte[] myBytes = byteArrayList.toArray(new Byte[0]);
        byte[] bytes = new byte[myBytes.length];

        for (int i = 0; i < myBytes.length; i++) {
            bytes[i] = myBytes[i];
        }

        return bytes;
    }

    private void writeBytes(byte[] bytes, String fileName) {

        String parentDir = "H:\\KOH\\Temp";
        File file = new File(parentDir, fileName);

        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ) {

            baos.write(bytes);
            baos.writeTo(bos);
//            bos.write(bytes);
//                bos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
