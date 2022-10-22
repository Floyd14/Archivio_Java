package model;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class Movie {

    private static int counter = 0;
    @Getter
    private final int id;
    @Getter
    private final String titolo;
    @Getter
    private final String autore;
    @Getter
    private final int anno;


    public Movie(String titolo, String autore, int anno) {
        super();

        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;

        this.id = counter++;
        log.info("Created movie's id: " + id);
    }

    @Override
    public String toString() {
        //String s = super.toString();
        return String.format("%s - %s di %s del %s", id, titolo, autore, anno);
    }
}

