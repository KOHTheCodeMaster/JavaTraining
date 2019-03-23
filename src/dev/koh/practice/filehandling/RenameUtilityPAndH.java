package dev.koh.practice.filehandling;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class RenameUtilityPAndH {
    private String basePath;

    private ArrayList<File> fileList;
    private ArrayList<String> dirList;
    private String extension;

    RenameUtilityPAndH(String basePath) {
        this.basePath = basePath;
        dirList = new ArrayList<>();
        fileList = new ArrayList<>();
        extension = ".";
        init();

    }

    public static void majorRename() {

        System.out.println("Enter Base Path (Root Dir.) : ");
        String temp = new Scanner(System.in).nextLine();

        RenameUtilityPAndH obj = new RenameUtilityPAndH(temp);

//        obj.showLists();
//        obj.sortFileList();
        boolean status = obj.renameFileList();

        if (status)
            System.out.println("Renamed " + obj.fileList.size() + " Files Successful.");
        else
            System.out.println("Renaming Failed...");

    }


    public static void main(String[] args) {

        //  Time Stamp: 23rd March 2K19, 10:13 PM..!!
        majorRename();

    }

    private void init() {

        //  In case of invalid basePath.
        verifyBasePath();

        initializeLists();
        verifyFileList();

        initializeExtension();
    }

    private void verifyFileList() {
        if (fileList.isEmpty()) {
            System.out.println("No Files Found...\nProgram Terminated...");
            System.exit(-102);
        }
    }

    private void verifyBasePath() {
        if (!validateBasePath()) {
            System.out.println("Invalid Directory Path...\nProgram Terminated...");
            System.exit(-101);
        }
    }

    private void initializeExtension() {
        String fName = fileList.get(0).getName();
        extension += fName.substring(fName.lastIndexOf('.') + 1);
    }

    private boolean validateBasePath() {
        return (new File(this.basePath).isDirectory());
    }

    /*
        Initialize the fileList & dirList by traversing the basePath directory.
        Note:   basePath directory is traversed upto single level, i.e. its not deeply traversed.
        Only the files & directories that reside inside basePath directory are visited,
        no further sub-directories are visited.
     */
    private void initializeLists() {
        File[] tempFileList = null;
        tempFileList = new File(basePath).listFiles();

//        assert tempFileList != null;
        for (File f : tempFileList) {
            if (f.isFile()) {
                this.fileList.add(f);
            } else
                this.dirList.add(f.getName());
        }
    }

    private void showLists() {

        System.out.println("Directories:");
        this.displayList(dirList);

        System.out.println("\nFiles:");
        this.displayList(fileList);

    }

    private void sortFileList() {

        ArrayList<Integer> intArr = extractIntegerArray();
        int size = intArr.size();

        for (int i = 0; i < size; i++) {
            //  The Loop Iterates not only just 1 less than the size of the list.
            //  but also, subtracting the 'i' terms which have already been shifted to the last,
            //  so, no need to compare with those terms, so Loop executes (size -i -1 times)

            for (int j = 0; j < size - i - 1; j++) {

                if (intArr.get(j) > intArr.get(j + 1)) {
                    swap(j, (j + 1), intArr);
                    File tempFile = fileList.get(j);
                    fileList.set(j, fileList.get(j + 1));
                    fileList.set(j + 1, tempFile);
                }
            }
        }

    }


    private ArrayList<Integer> extractIntegerArray() {

        ArrayList<Integer> intArrayList = new ArrayList<>();

        for (int i = 0; i < fileList.size(); i++) {
            String fName = fileList.get(i).getName();
            int num = extractNumBetweenParenthesis(fName);
            intArrayList.add(i, num);
//            System.out.println("i: " + i + " | A: " + intArrayList.get(i) + " | \"" + fName + "\"");
        }

        return intArrayList;
    }

    private void swap(int i, int j, ArrayList<Integer> arrayList) {

        Integer a = arrayList.get(i);
        Integer b = arrayList.get(j);

        a = a + b;
        b = a - b;
        a = a - b;

        arrayList.set(i, a);
        arrayList.set(j, b);

    }
    /*
        private void sortA(int[] intArray) {

            for (int i = 0; i < intArray.length -1; i++){
                int current = intArray[i];
                int next = intArray[i+1];
                System.out.println("c: " + current+ "| n: " + next);
                if( current > next ){
                    int temp = current;
                    current = next;
                    next = temp;
                }
            }

        }
    */

    private int extractNumBetweenParenthesis(String temp) {

        if (!temp.contains("("))
            return 0;

        int t1 = temp.lastIndexOf('(');
        int t2 = temp.lastIndexOf(')');

        return Integer.parseInt(temp.substring(t1 + 1, t2));
    }

    private boolean renameFileList() {

        sortFileList();

        if (dirList.size() != fileList.size()) {
            System.out.println("Number Of Directories != Number Of Files to be Renamed!");
            return false;
        }

        for (int i = 0; i < dirList.size(); i++) {
            File currentFile = fileList.get(i);
            boolean b = currentFile.renameTo(new File(basePath, "P & H - " + dirList.get(i) + extension));

            if (!b) {
                System.out.println("Failed to Rename: " + currentFile.getAbsolutePath());
                return false;
            }
        }

        return true;
    }

    /*
        private boolean fixFileNames() {

            for (File f : fileList) {
                String s = f.getName();
                int lastOpeningBracket = s.lastIndexOf('(');
                String ending = s.substring(lastOpeningBracket + 1);
                String temp = s.substring(0, lastOpeningBracket + 1);
                temp += "0000";
                temp += ending;
                System.out.println(temp);
            }


            return true;
        }
    */


    private void displayList(ArrayList list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

}
