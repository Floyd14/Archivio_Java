package model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class Movie {

    private static int counter = 0;
    @Getter
    private final int id;  //ha senso??
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String author;
    @Getter @Setter
    private int year;


    public Movie(String title, String author, int year) {
        super();

        this.title = title;
        this.author = author;
        this.year = year;

        this.id = counter++;
        log.info("Created movie's id: " + id);
    }

    @Override
    public String toString() {
        //String s = super.toString();
        return String.format("%s - %s di %s del %s", id, title, author, year);
    }
}

