package dev.koh.practice.filehandling.basic;

import java.io.IOException;
import java.io.InputStreamReader;

public class ISR {

    public static void main(String[] args) throws IOException {

        demonstrateISR();

    }

    private static void demonstrateISR() {

        try (InputStreamReader isr = new InputStreamReader(System.in)) {
            System.out.println("Enter Input: ");
            int i = isr.read();
            System.out.println("Input Chars.: ");

            while (isr.ready()) {
                System.out.print((char) i);
                i = isr.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
