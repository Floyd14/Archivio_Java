package model;


import lombok.extern.log4j.Log4j2;


@Log4j2
public class Movie {

    private static int counter = 0;
    private int id;
    private String title;
    private String author;
    private int year;

    public Movie(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Movie(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.id = counter++;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return title + ", author: " + author +", " + year;
    }
}

