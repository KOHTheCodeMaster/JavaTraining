package dev.koh.practice.sorting;

import java.util.ArrayList;

public class SortingAlgos {

    private ArrayList<Integer> a = new ArrayList<>();

    {
        //  4 5 2 1
        a.add(4);
        a.add(5);
        a.add(2);
        a.add(1);
    }

    public static void main(String[] args) {

        SortingAlgos obj = new SortingAlgos();

        System.out.println("Before Sorting,\nList: " + obj.a);

        obj.insertionSort();

        System.out.println("\nAfter Sorting,\nList: " + obj.a);

    }

    private void shellSort() {
        int len = a.size();

        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < len; i++) {
                int j = i;
                int temp = a.get(i);
                while (j >= gap && a.get(j - gap) > temp) {
                    a.set(j, a.get(j - gap));
                    j -= gap;
                }
                a.set(j, temp);
            }
        }

    }

    private void insertionSort2() {
        int pos = 0;    //  pos -> Index position of last sorted element.
        int len = a.size();

        //  4 4 5 1
        for (int i = 1; i < len; i++) {
            int min = pos;   //  index of Max. Element.
            int temp = a.get(i);
            if (a.get(i) < a.get(min)) {
                int j = -1;
                for (j = i; j > 0; j--) {
                    if (temp < a.get(min)) {
                        a.set(j, a.get(min));
                        min--;
                    }
                }
                a.set(j, temp);
            }
            pos++;
        }
    }

    private void insertionSort() {
        int pos = 0;    //  pos -> Index position of last sorted element.
        int len = a.size();

        //  4 4 5 1
        for (int i = 1; i < len; i++) {
            int temp = a.get(i);
            int j;
            for (j = i; j > 0 && a.get(j - 1) > temp; j--) {
                a.set(j, a.get(j - 1));
            }
            a.set(j, temp);
            pos++;
        }
    }

    private void selectionSort2() {
        //  Selection Sort - Min. - Left to Right.
        int pos = 0;  //  pos -> Index position of last sorted element.

        while (pos < a.size()) {
            int min = pos;   //  index of Max. Element.

            //  Iterate through list & find out the index of min. element.
            for (int i = pos; i < a.size(); i++) {
                if (a.get(i) < a.get(min))
                    min = i;
            }

            swap(a, pos, min);
            pos++;
        }
    }

    private void selectionSort() {
        //  Selection Sort - Max. - Right to Left.
        int pos = a.size() - 1;  //  pos -> Index position of last sorted element.

        while (pos > 0) {
            int max = 0;   //  index of Max. Element.

            //  Iterate through list & find out the index of max. element.
            for (int i = 0; i <= pos; i++) {
                if (a.get(i) > a.get(max))
                    max = i;
            }

            swap(a, pos, max);
            pos--;
        }
    }

    private void swap(ArrayList<Integer> a, int i, int j) {
        int temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    private void bubbleSort() {
        int pos = a.size() - 1; //  pos -> Index position of last sorted element.
        while (pos > 0) {
            //  Traversing the List
            for (int i = 0; i < pos; i++) {
                if (a.get(i) > a.get(i + 1)) {
                    //  Swap.
                    int temp = a.get(i);
                    a.set(i, a.get(i + 1));
                    a.set(i + 1, temp);
                }
            }
            pos--;
        }
    }

}

/*
 *  Time Stamp: 11th March 2K19, 05:32 PM..!!
 *
 *  Sorting Algorithms:
 *  1. Bubble Sort
 *  2. Selection Sort
 *  3. Insertion Sort
 *  4. Shell Sort
 *
 *  Code Developed By,
 *  K.O.H..!! ^__^
 *
 */
