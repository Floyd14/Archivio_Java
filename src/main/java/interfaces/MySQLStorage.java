package interfaces;

import lombok.extern.log4j.Log4j2;
import model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MySQLStorage implements Storage {

	private final String storageType;

	private Connection connect;

	public MySQLStorage() {
		this.storageType = "SQL";
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
		try {
			String queryString = "insert into movies (idmovies, titolo, autore, anno) " +
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
			String queryString = "select * from movies";
			Statement statement = connect.createStatement();
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
		try {
			String queryString = "update movies " +
					"set titolo = ? ," +
					"autore = ? ," +
					"anno  = ? " +
					"where idmovies = ?;";
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
					"from movies " +
					"where idmovies = ?";
			PreparedStatement st = connect.prepareStatement(queryString);
			st.setInt(1, movieId);
			st.executeUpdate();

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public int getNextId() {

		return 0;
	}

	@Override
	public String getStorageType() {
		return storageType;
	}
}


