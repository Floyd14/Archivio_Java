package interfaces;


import lombok.extern.log4j.Log4j2;
import model.Movie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

import static java.nio.charset.Charset.defaultCharset;
import static java.nio.file.StandardOpenOption.APPEND;

@Log4j2
public class TxtStorage implements Storage {

    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;
    private final File file;

    public TxtStorage() {
        this.file = new File("file.txt");
    }

    @Override
    public void connect() {
    }

    @Override
    public void disconnect() {
    }

    @Override
    public void addMovie(Movie movie) {
        Map<String, String> movieString = movieToMap(movie);
        try {
            bufferedWriter = Files.newBufferedWriter(
                    file.toPath(),
                    defaultCharset(),
                    APPEND);
            bufferedWriter.write(movieString.toString() + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            System.err.println("An error occurred.");
            log.error(e);
            e.getStackTrace();
        }
    }

    @Override
    public List<Movie> readMovies() {
        List<Movie> movies = new ArrayList<>();

        try {
            bufferedReader = Files.newBufferedReader(file.toPath());
            Stream<String> rawData = bufferedReader.lines();

            List<String> rawDataList = rawData.toList();

            for (String line : rawDataList) {
                System.out.println(line);

                Map<String, String> movieMap = new HashMap<>();
                String[] pairs = line
                        .replace("{", "")
                        .replace("}", "")
                        .split(",");
                for (String pair : pairs) {
                    String[] keyValue = pair.split("=");
                    movieMap.put(keyValue[0].trim(), keyValue[1].trim());
                }
                Movie movie = mapToMovie(movieMap);
                movies.add(movie);
            }
            bufferedReader.close();
        } catch (Exception e) {
        }
        return movies;
    }

    @Override
    public void updateMovie(Movie movie) {

    }

    @Override
    public void deleteMovie(int movieId) {
        List<Movie> movies = readMovies();
        movies.remove(movieId);
        // cancello tutto, ricreo i movie e aggiorno il file
        try {
            bufferedWriter = Files.newBufferedWriter(
                    file.toPath(),
                    defaultCharset());

            for (Movie movie : movies) {
                addMovie((movie));
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
        }
    }

    private Map<String, String> movieToMap(Movie movie) {
        Map<String, String> movieMap = new LinkedHashMap<>();
        movieMap.put("ID", Integer.toString(movie.getId()));
        movieMap.put("Title", movie.getTitle());
        movieMap.put("Author", movie.getAuthor());
        movieMap.put("Anno", Integer.toString(movie.getYear()));
        return movieMap;
    }

    private Movie mapToMovie(Map<String, String> data) {
        Movie movie = new Movie(
                data.get("Title"),
                data.get("Author"),
                Integer.parseInt(data.get("Anno")));
        return movie;
    }
}


//    public static void writeDataForCustomSeparatorCSV(String filePath)
//    {
//
//        // first create file object for file placed at location
//        // specified by filepath
//        File file = new File(filePath);
//
//        try {
//            // create FileWriter object with file as parameter
//            FileWriter outputfile = new FileWriter(file);
//
//            // create CSVWriter with '|' as separator
//            CSVWriter writer = new CSVWriter(outputfile, '|',
//                    CSVWriter.NO_QUOTE_CHARACTER,
//                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
//                    CSVWriter.DEFAULT_LINE_END);
//
//            // create a List which contains String array
//            List<String[]> data = new ArrayList<String[]>();
//            data.add(new String[] { "Name", "Class", "Marks" });
//            data.add(new String[] { "Aman", "10", "620" });
//            data.add(new String[] { "Suraj", "10", "630" });
//            writer.writeAll(data);
//
//            // closing writer connection
//            writer.close();
//        }
//        catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
