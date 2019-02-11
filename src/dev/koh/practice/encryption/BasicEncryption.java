package dev.koh.practice.encryption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BasicEncryption {

    public static void main(String[] args) throws IOException {

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(inputStreamReader);

        System.out.println("Enter The Password: ");
        String password = br.readLine();

        Password obj = new Password(password);
        obj.showEncryptedPassword();
        obj.login("b");

    }

}

class Password {

    private final static int KEY = 97;
    private String encryptedPassword;

    {
        encryptedPassword = null;
    }

    public Password(String password) {
        this.encryptedPassword = encrypt(password);
    }

    private String encrypt(String password) {

        String enc = "";
        for (int ch : password.toCharArray()) {
            enc += (ch ^ KEY);
            System.out.println("ch: " + ch + " | enc : " + enc);
        }
        return enc;
    }

    void showEncryptedPassword() {
        System.out.println("Password: " + this.encryptedPassword);
    }

    void login(String password) {
        if (verifyPassword(password))
            System.out.println("Logged In Successfully!");
        else
            System.out.println("Invalid Password!");
    }

    private boolean verifyPassword(String password) {
        return ((encrypt(password)).equals(this.encryptedPassword));
    }

}

