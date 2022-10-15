package Interfaces;

import java.awt.*;
import java.io.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TxtInterfcace implements Interface {
    private final File file = new File("filename.txt");

    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;


    @Override
    public boolean connect() {
        try {
            this.fileReader = new FileReader(file);
            //connected = true;
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean disconnect() throws IOException {
        this.fileReader.close();
        return true;
    }

    public void readAll() {
//        while (this.fileReader.read()) {
//            String data = fileReader.read();
//            System.out.println(data);
//        }
    }
}
