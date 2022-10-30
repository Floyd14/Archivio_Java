package interfaces;

import lombok.extern.log4j.Log4j2;
import model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MySQLStorage implements Storage {

	private Connection connect;

	public MySQLStorage() {
	}

	@Override
	public void connect() {
		try {
			if (connect == null || connect.isClosed())
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/test",
						"root",
						"Ytrewq1!"
				);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void disconnect() {
		try {
			if (connect != null && !connect.isClosed()) {
				connect.close();
			}
		} catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
	}

	@Override
	public void addMovie(Movie movie) {
       // to be implemented
	}

	@Override
	public List<Movie> readMovies() {
		try {
			List<Movie> movieList = new ArrayList<>();
			String queryString = "select * from movies";
			Statement statement = connect.createStatement();
			//    private PreparedStatement preparedStatement = null;
			ResultSet resultSet = statement.executeQuery(queryString);

			while (resultSet.next()) {
				int id = resultSet.getInt("idmovies");
				String title = resultSet.getString("titolo");
				String author = resultSet.getString("autore");
				int year = resultSet.getInt("anno");
				Movie movie = new Movie(id, title, author, year);
				movieList.add(movie);
			}
			return movieList;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public void updateMovie(Movie movie) {
		// to be implemented
	}

	@Override
	public void deleteMovie(int movieId) {
		// to be implemented
	}

	@Override
	public int getNextId() {
		return 0;
	}
}


