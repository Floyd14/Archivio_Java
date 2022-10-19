package src.model;

public class Movie {

    private static int counter = 0;
    private final int id;
    private final String titolo;
    private final String autore;
    private final int anno;

    // default const
    public Movie(String titolo, String autore, int anno) {
        super();

        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;

        // instance var
        this.id = counter++;
    }

    // metodo static non estendibile
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        //String s = super.toString();
        return String.format("%s - %s di %s del %s", id, titolo, autore, anno);
    }
}

