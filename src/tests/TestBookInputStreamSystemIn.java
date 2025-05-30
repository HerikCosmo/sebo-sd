package tests;

import communication.BookInputStream;

import java.io.IOException;

public class TestBookInputStreamSystemIn {
    public static void main(String[] args) {
        try(BookInputStream bis = new BookInputStream(System.in)) {
            bis.readAllBooks();
            System.out.println("Cabouse");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
