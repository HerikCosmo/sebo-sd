package questoes.Q03;

import communication.LivroInputStream;

import java.io.IOException;

public class TestLivroInputStreamSystemIn {
    public static void main(String[] args) {
        try(LivroInputStream bis = new LivroInputStream(System.in)) {
            bis.readAllLivros();
            System.out.println("Fim");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
