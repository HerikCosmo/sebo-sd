package model;

import java.io.Serial;

public class Apostila extends Produto {
  @Serial
  private static final long serialVersionUID = 1L;
  private String subject;
  private String educationalInstitution;
  private String professor;

  public Apostila(
      String id,
      String title,
      double price,
      String description,
      String condition,
      String subject,
      String educationalInstitution,
      String professor) {
    super(id, title, price, description, condition);
    this.subject = subject;
    this.educationalInstitution = educationalInstitution;
    this.professor = professor;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getProfessor() {
    return professor;
  }

  public void setProfessor(String professor) {
    this.professor = professor;
  }

  public String getEducationalInstitution() {
    return educationalInstitution;
  }

  public void setEducationalInstitution(String educationalInstitution) {
    this.educationalInstitution = educationalInstitution;
  }

  @Override
  public String toString() {
    return "Apostila{" +
        super.toString() +
        ", subject='" + subject + '\'' +
        ", educationalInstitution='" + educationalInstitution + '\'' +
        ", professor='" + professor + '\'' +
        '}';
  }
}
