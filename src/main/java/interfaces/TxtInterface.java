package interfaces;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.charset.Charset.defaultCharset;

public class TxtInterface implements Interface {

    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;
    private static Files file;

    public TxtInterface(Path filePath) {

        try {
            this.bufferedWriter = Files.newBufferedWriter(filePath, defaultCharset());
        } catch (Exception e) {
            System.err.println("An error occurred on" + bufferedWriter);
        }

//        try {
//            OutputStream os = Files.newOutputStream(filePath);
//        } catch (Exception e) {
//
//        }
    }

    @Override
    public void connect(Path filePath) {
        boolean exists = Files.exists(filePath);
        System.out.println("exists = " + exists);
    }

    @Override
    public void disconnect() {}

    @Override
    public void writeLine(){
        try {
            this.bufferedWriter.write("Prova\n");
            this.bufferedWriter.flush();
        } catch (Exception e){
            System.err.println("An error occurred.");
            e.getStackTrace();
        }
        disconnect();
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
