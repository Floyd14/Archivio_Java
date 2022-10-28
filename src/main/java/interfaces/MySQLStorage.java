package interfaces;

import model.Movie;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLStorage implements Storage {

    private Connection connect;
    private Statement statement;
    //    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet;

    public MySQLStorage() {
    }

    @Override
    public void connect() {
        try {
            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test",
                    "root",
                    "Ytrewq1!"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void disconnect() {
        if (connect != null) {
            try {
                connect.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void addMovie(Movie movie) {

    }

    @Override
    public List<Movie> readMovies() {
        try {
            List<Movie> movieList = new ArrayList<>();
            String queryString = "select * from movies";
            statement = connect.createStatement();
            resultSet = statement.executeQuery(queryString);

//            System.out.println(
//                    resultSet.getMetaData().getTableName(1)
//            );
//            for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
//                System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
//            }

            while (resultSet.next()) {
                String idmovies = resultSet.getString("idmovies");
                String title = resultSet.getString("titolo");
                String author = resultSet.getString("autore");
                int year = resultSet.getInt("anno");

                Movie movie = new Movie(title, author, year);
                movieList.add(movie);
            }
            return movieList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateMovie(Movie movie) {

    }

    @Override
    public void deleteMovie(int movieId) {

    }

    public static void main(String[] args) {
        MySQLStorage test = new MySQLStorage();
        test.connect();
        test.readMovies();
    }
}


