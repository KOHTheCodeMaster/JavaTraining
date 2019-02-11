package dev.koh.practice.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BubbleSort {

    private ArrayList<Integer> arrayList;
    private int size;

    BubbleSort() {
        size = 0;
        arrayList = new ArrayList<>(size);
    }

    private static int readNum() {

        int result = 0;
        boolean invalidFlag = true;

        while (invalidFlag) {
            try {
                //  Need to handle multiple inputs separated by spaces in a single line.
                result = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
                invalidFlag = false;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid Integer.");
            } catch (IOException e) {
                System.out.println("Some Technical Glitch Occured.");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Unknown Exception Caught!");
                e.getMessage();
            }
        }
        return result;
    }

    void createList() {

        System.out.println("Enter the Size of the List: ");
        setSize(readNum());
        if (getSize() <= 0) {
            System.out.println("Enter a valid size.");
            createList();
        }

        System.out.format("Enter [%d] Integer Values: ", this.getSize());

        arrayList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            arrayList.add(readNum());
        }
        System.out.println("Array List Created Successfully!");

    }

    ArrayList<Integer> bubbleSort(ArrayList<Integer> list) {

        for (int i = 0; i < this.size; i++) {
            //  The Loop Iterates not only just 1 less than the size of the list.
            //  but also, subtracting the 'i' terms which have already been shifted to the last,
            //  so, no need to compare with those terms, so Loop exectutes (size -i -1 times)

            for (int j = 0; j < list.size() - i - 1; j++) {

                if (list.get(j) > list.get(j + 1))
                    swap(j, (j + 1));
            }
        }

        System.out.println("Sorted List:");
        printList(list);

        return list;
    }

    ArrayList<Integer> bubbleSort() {

        for (int i = 0; i < this.size; i++) {
            //  The Loop Iterates not only just 1 less than the size of the list.
            //  but also, subtracting the 'i' terms which have already been shifted to the last,
            //  so, no need to compare with those terms, so Loop exectutes (size -i -1 times)

            for (int j = 0; j < arrayList.size() - i - 1; j++) {

                if (arrayList.get(j) > arrayList.get(j + 1))
                    swap(j, (j + 1));
            }
        }

        System.out.println("Sorted List:");
        printList(arrayList);

        return arrayList;
    }

    private void swap(int i, int j) {

        int a = arrayList.get(i);
        int b = arrayList.get(j);

        a = a + b;
        b = a - b;
        a = a - b;

        arrayList.set(i, a);
        arrayList.set(j, b);


    }

    void displayList() {
        printList(this.getArrayList());
    }

    void printList(ArrayList<Integer> list) {
        for (int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }
}
