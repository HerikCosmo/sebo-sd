package questoes.Q02;

import communication.LivroOutputStream;
import model.Livro;


import java.io.IOException;

public class TestLivroOutputStreamSystemOut {
    public static void main(String[] args) {
        Livro[] livros = {
                new Livro("1", "O Guia dos Mochileiros das Galáxias", 15.99, "Comédia e Sci-fi", "Usado", "Douglas Adams", "978-0345391803", 1979, "Ficção Científica"),
                new Livro("2", "1984", 12.50, "Novela Distópica", "Usado", "George Orwell", "978-0451524935", 1949, "Dystopian"),
                new Livro("3", "Orgulho e Preconceito", 10.00, "Romance clássico.", "Novo", "Jane Austen", "978-0486284738", 1813, "Romance")
        };

        try (LivroOutputStream bos = new LivroOutputStream(livros, 3, System.out)) {
            System.out.println("System.out");
            bos.writeAllBooks();
            System.out.println("Fim da Leitura");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
