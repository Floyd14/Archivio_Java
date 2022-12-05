package storage;

import lombok.extern.log4j.Log4j2;
import model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MySQLStorage implements Storage {

	private final String table;

	private Connection connect;

	public MySQLStorage(String table) {
		this.table = table;
	}

	public MySQLStorage() {this("movies");}

	@Override
	public void connect() {
		try {
			if (connect == null || connect.isClosed())
				connect = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/Prod",
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
		try {
			String queryString = "insert into " + table + " (id, titolo, autore, anno) " +
					"values (null,?,?,?);";
			PreparedStatement st = connect.prepareStatement(queryString);

			st.setString(1, movie.getTitle());
			st.setString(2, movie.getAuthor());
			st.setInt(3, movie.getYear());

			st.executeUpdate();

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public List<Movie> readMovies() {
		try {
			List<Movie> movieList = new ArrayList<>();
			String queryString = "select * from " + table;
			Statement statement = connect.createStatement();
			ResultSet resultSet = statement.executeQuery(queryString);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
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
		try {
			String queryString = "update " + table + " " +
					"set titolo = ? ," +
					"autore = ? ," +
					"anno  = ? " +
					"where id" + " = ?;";
			PreparedStatement st = connect.prepareStatement(queryString);

			st.setString(1, movie.getTitle());
			st.setString(2, movie.getAuthor());
			st.setInt(3, movie.getYear());
			st.setInt(4, movie.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void deleteMovie(int movieId) {
		try {
			String queryString = "delete " +
					"from " + table + " " +
					"where id" + " = ?";
			PreparedStatement st = connect.prepareStatement(queryString);
			st.setInt(1, movieId);
			st.executeUpdate();

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public StorageType getStorageType() {
		return StorageType.SQL;
	}
}


