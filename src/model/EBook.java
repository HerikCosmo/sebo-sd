package model;

import java.io.Serial;

public class EBook extends Produto {
  @Serial
  private static final long serialVersionUID = 1L;
  private String author;
  private String format;
  private double fileSizeMB;

  public EBook(
      String id,
      String title,
      double price,
      String description,
      String condition,
      String author,
      String format,
      double fileSizeMB) {
    super(id, title, price, description, condition);
    this.author = author;
    this.format = format;
    this.fileSizeMB = fileSizeMB;
  }

  // Getters and Setters
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public double getFileSizeMB() {
    return fileSizeMB;
  }

  public void setFileSizeMB(double fileSizeMB) {
    this.fileSizeMB = fileSizeMB;
  }

  @Override
  public String toString() {
    return "EBook{" +
        super.toString() +
        ", author='" + author + '\'' +
        ", format='" + format + '\'' +
        ", fileSizeMB=" + fileSizeMB +
        '}';
  }
}
