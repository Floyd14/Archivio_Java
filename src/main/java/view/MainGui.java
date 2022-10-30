package view;

import controller.Controller;
import model.Movie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class MainGui extends JFrame {
	private JList<Movie> movieJList;
	private JTextField textFieldTitle;
	private JTextField textFieldAuthor;
	private JTextField textFieldYear;
	private JRadioButton inMemoryRadioButton;
	private JButton addButton;
	private JButton deleteButton;
	private JPanel panelMain;
	private JButton updateButton;
	private JRadioButton txtRadioButton;
	private JRadioButton mySQLRadioButton;
	private ButtonGroup radioButtonGroup;
	private final Controller controllerGUI;
	private List<Movie> movies;
	private DefaultListModel<Movie> moviesListModel;

	MainGui() {
		super("Archivio");
		this.setContentPane(this.panelMain);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

		updateButton.setEnabled(false);
		deleteButton.setEnabled(false);
		//------------------------------
		this.controllerGUI = new Controller();
		this.moviesListModel = new DefaultListModel<>();

		movieJList.setModel(moviesListModel);
		refreshScreenList();
		//------------------------------


		addButton.addActionListener(e -> {
			try {
				controllerGUI.addMovie(
						textFieldTitle.getText(),
						textFieldAuthor.getText(),
						Integer.parseInt(textFieldYear.getText())

				);
			} catch (NumberFormatException numberFormatException) {
				JOptionPane.showMessageDialog(this,
						"Inserire un numero per l'anno",
						"Errore",
						JOptionPane.ERROR_MESSAGE);
			}
			refreshScreenList();
		});

		deleteButton.addActionListener(e -> {
			int movieIndex = movieJList.getSelectedIndex();
			if (movieIndex >= 0) {
				controllerGUI.deleteMovie(movieIndex);
				refreshScreenList();
			}
		});
		movieJList.addListSelectionListener(e -> {
			int movieIndex = movieJList.getSelectedIndex();
			if (movieIndex >= 0) {
				Movie movie = movies.get(movieIndex);
				textFieldTitle.setText(movie.getTitle());
				textFieldAuthor.setText(movie.getAuthor());
				textFieldYear.setText(String.valueOf(movie.getYear()));

				updateButton.setEnabled(true);
				deleteButton.setEnabled(true);
			} else {
				updateButton.setEnabled(false);
				deleteButton.setEnabled(false);
			}
		});
		updateButton.addActionListener(e -> {
			int movieIndex = movieJList.getSelectedIndex();
			if (movieIndex >= 0) {
				controllerGUI.updateMovie(
						movieIndex,
						textFieldTitle.getText(),
						textFieldAuthor.getText(),
						Integer.parseInt(textFieldYear.getText())
				);
				refreshScreenList();
			}
		});


		ItemListener listener = e -> {

		};
		inMemoryRadioButton.addItemListener(listener);
		txtRadioButton.addItemListener(listener);
		mySQLRadioButton.addItemListener(listener);
	}

	private void refreshScreenList() {
		movies = controllerGUI.getMovies();
		moviesListModel.removeAllElements();
		for (Movie movie : movies) {
			moviesListModel.addElement(movie);
		}
	}

	public static void main(String[] args) {
		MainGui screen = new MainGui();
		screen.setVisible(true);
	}
}
