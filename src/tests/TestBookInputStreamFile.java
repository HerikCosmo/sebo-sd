package tests;

import communication.BookInputStream;

import java.io.FileInputStream;
import java.io.IOException;

public class TestBookInputStreamFile {
    public static void main(String[] args) {
        String filename = "books_data.bin";

        try(FileInputStream fis = new FileInputStream(filename);
            BookInputStream bis = new BookInputStream(fis)) {
            System.out.println("Lendo Arquivo: " + filename);
            bis.readAllBooks();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
