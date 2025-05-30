package model;

import java.io.Serializable;

public class Book extends Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String author;
    private String isbn;
    private int publicationYear;
    private String genre;

    public Book(
            String id,
            String title,
            double price,
            String description,
            String condition,
            String author,
            String isbn,
            int publicationYear,
            String genre
            ) {
        super(id, title, price, description, condition);
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                super.toString() +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                ", genre='" + genre + '\'' +
                '}';
    }
}
