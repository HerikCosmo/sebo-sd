package model;

import java.io.Serial;
import java.io.Serializable;

public class DVD extends Produto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String director;
    private int durationMinutes;

    public DVD(
            String id,
            String title,
            double price,
            String description,
            String condition,
            String director,
            int durationMinutes
    ) {
        super(id, title, price, description, condition);
        this.director = director;
        this.durationMinutes = durationMinutes;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "director='" + director + '\'' +
                ", durationMinutes=" + durationMinutes +
                '}';
    }
}
