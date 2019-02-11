package dev.koh.practice.sorting;

import java.util.Scanner;

public class SimpleBubbleSortExample {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Size: ");
        int size = scanner.nextInt();

        System.out.println("Enter Elements for List: ");

        int[] list = new int[size];

        for (int i = 0; i < size; i++) {
            list[i] = scanner.nextInt();
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }

        System.out.println("Sorted:");

        for (int i : list) {
            System.out.println(i + " ");
        }

    }

}
