package dev.koh.practice.filehandling.randomaccessfile.FileDestroyer;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileDestroyer {

    private static long sharedCurrentFilePointer;
    private final long DMG_PERCENTAGE = 20;
    private final int BUFFER_SIZE = 8;  //  [MB]
    private final int ONE_MB = (1 << 20);
    private final int TEN_MB = ONE_MB * 10;
    private final long FILE_SIZE_THRESHOLD_LIMIT = ONE_MB * 64;
    private long fileLength;
    private boolean damageEntireFile;
    private String currentAbsoluteFilePath;


    public static void main(String[] args) {

        System.out.println("Begin.");

        new FileDestroyer().major();

        System.out.println("\nEnd.");

    }

    private void major() {

        //  User Input for filePath
        try {
            boolean invalidFilePath = true;
            File file = null;
            while (invalidFilePath) {
                System.out.println("Enter Valid File Path : ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String filePath = br.readLine();

                System.out.println("Wanna Delete Entire File? [Y/N] : ");
                char ch = br.readLine().charAt(0);

                if (ch == 'y' || ch == 'Y')
                    damageEntireFile = true;

                file = new File(filePath);
                if (!(file.isFile() || file.isDirectory())) {
                    System.out.println("Not a valid File/Dir. Path!");
                    invalidFilePath = true;
                } else invalidFilePath = false;
            }

            System.out.println("File To Be Destroyed: \n" + file.getCanonicalPath());

            if (file.isFile())
                damageFile(file);
            else if (file.isDirectory()) {

                DirTreeWalker dirTreeWalker = new DirTreeWalker();
                Files.walkFileTree(file.toPath(), dirTreeWalker);

//                Thread.sleep();
                System.out.println("\nFiles Destroyed : " + dirTreeWalker.filesCount);
                System.out.println("Dirs. Visited : " + dirTreeWalker.dirsCount);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void damageFile(File file) {

        this.fileLength = file.length();

        try (RandomAccessFile raf = new RandomAccessFile(file, "rwd")) {

            if (damageEntireFile || (fileLength < FILE_SIZE_THRESHOLD_LIMIT)) {

                //  DAMAGE Entire File
                removeEntireFile(raf);
                return;
            }

            //  Otherwise, Damage DMG_PERCENTAGE % of the File

//            final int ONE_KB = (int) FileSize.KILO_BYTE.getSizeInBytes();
            removeFileHeaderAndFooter(raf);
            removeMajorFileSegment(raf);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I/O Exception Occurred!" +
                    "\nProgram Terminated...");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Interrupted Exception Occurred!" +
                    "\nProgram Terminated...");
        }

    }

    private void removeEntireFile(RandomAccessFile raf) throws IOException, InterruptedException {

        byte[] buffer = new byte[ONE_MB * 8];

        System.out.println("\nCurrently Processing : [" + currentAbsoluteFilePath + "]");

        Runnable runnable = () -> {
            /*
                Time Stamp : 22nd August 2K19, 12:56 AM..!!
                sharedCurrentFilePointer -> value of i i.e. current Pos.
                        Following Condition :
                (sharedCurrentFilePointer + buffer.length > fileLength) == true
                only when the Main Thread has completed the Processing.
             */
            while (sharedCurrentFilePointer + buffer.length < fileLength) {
                System.out.print((sharedCurrentFilePointer * 100 / fileLength) + "%");

                try {
                    Thread.sleep(10);
//                        this.wait(1000);
                    System.out.print("\b\b\b");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("\b\b\b");
            System.out.println("100%\nFile Destroyed Successfully!");

        };
        Thread displayPercentageThread = new Thread(runnable);
        displayPercentageThread.start();

        //  DAMAGE Entire File!
        for (long i = 0; i < fileLength; i += buffer.length) {
            raf.seek(i);
            raf.write(buffer);
            sharedCurrentFilePointer = i;
        }
        Thread.sleep(20);

    }

    private void removeMajorFileSegment(RandomAccessFile raf) throws IOException, InterruptedException {

        byte[] buffer = new byte[ONE_MB * BUFFER_SIZE];
        long numOfBytesToDestroy = (fileLength * DMG_PERCENTAGE) / 100;
        long numOfPartitions = fileLength * buffer.length / numOfBytesToDestroy;

        Runnable runnable = () -> {
            /*
                Time Stamp : 22nd August 2K19, 12:26 AM..!!
                sharedCurrentFilePointer -> value of i i.e. current Pos.
                        Following Condition :
                (sharedCurrentFilePointer + numOfPartitions > fileLength - TEN_MB) == true
                only when the Main Thread has completed the Processing.
             */
            while (sharedCurrentFilePointer + numOfPartitions < fileLength - TEN_MB) {
                System.out.print((sharedCurrentFilePointer * 100 / fileLength) + "%");

                try {
                    Thread.sleep(10);
                    System.out.print("\b\b\b");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            System.out.print("\n100%\nFile Destroyed Successfully!");
            System.out.print("\b\b\b");
            System.out.println("100%\nFile Destroyed Successfully!");


        };
        Thread displaYPercentageThread = new Thread(runnable);
        displaYPercentageThread.start();

        for (long i = TEN_MB; i < fileLength - TEN_MB; i += numOfPartitions) {

            //  DAMAGE File Header
            raf.seek(i);
            raf.write(buffer);
            sharedCurrentFilePointer = i;

        }
        Thread.sleep(20);
    }

    private void removeFileHeaderAndFooter(RandomAccessFile raf) throws IOException {

        byte[] tenMBBuffer = new byte[TEN_MB];

        //  DAMAGE File Header
        raf.seek(0);
        raf.write(tenMBBuffer);

        //  DAMAGE File Footer
        long footerPos = (fileLength - tenMBBuffer.length);
//        System.out.println("fp: " + footerPos + " | fL : " + fileLength);
        raf.seek(footerPos);
        raf.write(tenMBBuffer);

    }

    public class DirTreeWalker extends SimpleFileVisitor<Path> {

        long filesCount;
        long dirsCount;

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            dirsCount++;
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

            //  Time Stamp : 22nd August 2K19, 01:57 AM..!!
            filesCount++;
            currentAbsoluteFilePath = file.toAbsolutePath() + "";
            damageFile(file.toFile());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.out.println("\nFAILED to Visit File. : " + file.toAbsolutePath() + "\n");
            System.out.println(exc.getMessage());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            return FileVisitResult.CONTINUE;
        }
    }

}

/*
 *  Date Created : 21st August 2K19, 09:44 PM..!!
 *  Time Stamp : 22nd August 2K19, 12:56 AM..!!
 *
 *  Change Log:
 *
 *  Time Stamp : 22nd August 2K19, 01:59 AM..!!
 *  Init Commit - File Destroyer..!!
 *
 *  Code Developed By,
 *  ~K.O.H..!! ^__^
 */
