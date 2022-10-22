package model;

import java.util.logging.Logger;

public class Movie {

    private static int counter = 0;
    private final int id;
    private final String titolo;
    private final String autore;
    private final int anno;

    private static final Logger LOGGER = Logger.getLogger( Movie.class.getName() );


    public Movie(String titolo, String autore, int anno) {
        super();

        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;

        this.id = counter++;

        LOGGER.info("Logging an INFO-level message");
    }

    public String getTitolo() {

        return titolo;
    }

    public String getAutore() {

        return autore;
    }

    public int getAnno() {

        return anno;
    }

    public int getId() {

        return this.id;
    }

    @Override
    public String toString() {
        //String s = super.toString();
        return String.format("%s - %s di %s del %s", id, titolo, autore, anno);
    }
}

