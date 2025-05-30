package model;

import java.io.Serial;
import java.io.Serializable;

public class CD extends Produto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String artist;
    private int durationMinutes;
    private String format;

    public CD(
            String id,
            String title,
            double price,
            String description,
            String condition,
            String artist,
            int durationMinutes,
            String format
    ) {
        super(id, title, price, description, condition);
        this.artist = artist;
        this.durationMinutes = durationMinutes;
        this.format = format;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "CD{" +
                super.toString() +
                ", artist='" + artist + '\'' +
                ", durationMinutes=" + durationMinutes +
                ", format='" + format + '\'' +
                '}';
    }
}
