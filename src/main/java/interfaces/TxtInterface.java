package interfaces;


import lombok.extern.log4j.Log4j2;
import model.Movie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.Charset.defaultCharset;
import static java.nio.file.StandardOpenOption.APPEND;

@Log4j2
public class TxtInterface implements Interface {
//caso
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;
    private static Files file;

    public TxtInterface(Path filePath) {

        try {
            this.bufferedWriter = Files.newBufferedWriter(
                    filePath,
                    defaultCharset(),
                    APPEND);  // fa schifo..
            this.bufferedReader = Files.newBufferedReader(filePath);
        } catch (Exception e) {
            System.err.println("An error occurred on" + bufferedWriter);
        }
    }

    @Override
    public void connect(Path filePath) {

        boolean exists = Files.exists(filePath);
        System.out.println("exists = " + exists);
    }

    @Override
    public void disconnect() {}

    @Override
    public void writeLine(Map movieMap){

        try {
            bufferedWriter.write(movieMap.toString() + "\n");
            bufferedWriter.flush();
            throw new RuntimeException();
        } catch (Exception e){
            System.err.println("An error occurred.");
            log.error(e);
            e.getStackTrace();
        }
        disconnect();
    }

    @Override
    public List<String> readAll(){

        List<String> data = new ArrayList<>();
        String strCurrentLine;
        try {
            while ((strCurrentLine = bufferedReader.readLine()) != null)
                data.add(strCurrentLine);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(data);

        parseDataToMovie(data);
        return data;
    }

    public List<Movie> parseDataToMovie(List<String> data) {

        List<Movie> movieList = new ArrayList<>();

        for (int i=0; i < data.size(); i++) {
            // prendo una linea alla volta
            String line = data.get(i)
                    .replace("=", ":")
                    .replaceAll("\\{", "")
                    .replaceAll("}", "");
            //System.out.println(line);

            // converto la String in mappa
            Map<String, String> mapString = new HashMap<>();
            String[] pairs = line.split(",");
            for (int y=1; y<pairs.length; y++) {
                String pair = pairs[y].trim();
                //System.out.println(pair);

                String[] keyValue = pair.split(":");
                mapString.put(keyValue[0], keyValue[1]);
            }
            // Dalla mappa creo il movie e lo aggiungo alla lista
            movieList.add(new Movie(
                    mapString.get("Title"),
                    mapString.get("Author"),
                    Integer.parseInt(mapString.get("Anno"))
                    ));
        }
        //System.out.println(movieList);

//        String[] pairs = s.split(",");
//            for (int i=0;i<pairs.length;i++) {
//                String pair = pairs[i];
//                String[] keyValue = pair.split(":");
//                myMap.put(keyValue[0], Integer.valueOf(keyValue[1]))

        return movieList;
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

}
