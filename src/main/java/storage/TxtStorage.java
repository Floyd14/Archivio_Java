package storage;


import com.mysql.cj.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import model.Movie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.charset.Charset.defaultCharset;
import static java.nio.file.StandardOpenOption.APPEND;

@Log4j2
public class TxtStorage implements IdentifierGeneratingStorage {

    private final File file;


    public TxtStorage(File file) {
        this.file = file;
    }

    public TxtStorage() {
        this(new File("file.txt"));
    }

    @Override
    public void connect() {
        //Not used
    }

    @Override
    public void disconnect() {
        //Not used
    }

    @Override
    public void addMovie(Movie movie) {
        movie.setId(getNextId());
        String movieString = serialize(movie);
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(
                    file.toPath(),
                    defaultCharset(),
                    APPEND);
            bufferedWriter.write(movieString + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void writeMovies(List<Movie> movies) {
        String movieString = movies.stream()
                .map(this::serialize)
                .collect(Collectors.joining("\n"));
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(
                    file.toPath(),
                    defaultCharset());
            bufferedWriter.write(movieString + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Movie> readMovies() {
        List<Movie> movies = null;
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(file.toPath());
            movies = bufferedReader.lines()
                    .filter(line -> !StringUtils.isNullOrEmpty(line))
                    .map(this::deserialize)
                    .collect(Collectors.toList());
            bufferedReader.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return movies;
    }

    @Override
    public void updateMovie(Movie movie) {
        List<Movie> movies = readMovies();
        movies.forEach(element -> {
            if (element.getId() == movie.getId()) {
                element.setTitle(movie.getTitle());
                element.setAuthor(movie.getAuthor());
                element.setYear(movie.getYear());
            }
        });
        writeMovies(movies);
    }

    @Override
    public void deleteMovie(int movieId) {
        List<Movie> movies = readMovies();

        movies = movies.stream()
                .filter(movie -> movie.getId() != movieId)
                .collect(Collectors.toList());

        writeMovies(movies);
    }

    @Override
    public int getNextId() {
        int id = 0;
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(file.toPath());
            List<Integer> ids = bufferedReader.lines()
                    .filter(line -> !StringUtils.isNullOrEmpty(line))
                    .map(line -> Integer.parseInt(line.split(",")[0])).toList();
            bufferedReader.close();
            id = Collections.max(ids)+1;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return id;
    }

    @Override
    public StorageType getStorageType() {
        return StorageType.TXT;
    }

    private String serialize(Movie movie) {
        return movie.getId() + ","
                + movie.getTitle() + ","
                + movie.getAuthor() + ","
                + movie.getYear();
    }

    private Movie deserialize(String row) {
        List<String> tokens = Arrays.asList(row.split(","));
        if (!tokens.isEmpty()) {
            return new Movie(
                    Integer.parseInt(tokens.get(0)),
                    tokens.get(1),
                    tokens.get(2),
                    Integer.parseInt(tokens.get(3))
            );
        } else {
            throw new IllegalArgumentException("Error in deserializing empty line");
        }
    }


}