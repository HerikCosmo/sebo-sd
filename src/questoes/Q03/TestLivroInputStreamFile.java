package questoes.Q03;

import communication.LivroInputStream;

import java.io.FileInputStream;
import java.io.IOException;

public class TestLivroInputStreamFile {
    public static void main(String[] args) {
        String filename = "books_data.bin";

        try(FileInputStream fis = new FileInputStream(filename);
            LivroInputStream bis = new LivroInputStream(fis)) {
            System.out.println("Lendo Arquivo: " + filename);
            bis.readAllLivros();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
