package view;

import controller.Controller;
import storage.StorageType;
import model.Movie;

import javax.swing.*;

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
	private final DefaultListModel<Movie> moviesDLM;

	public MainGui() {
		super("Archivio");
		this.setContentPane(this.panelMain);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

		radioButtonGroup.setSelected(txtRadioButton.getModel(), true);
		updateButton.setEnabled(false);
		deleteButton.setEnabled(false);

		//------------------------------

		this.controllerGUI = new Controller(StorageType.TXT);
		this.moviesDLM = new DefaultListModel<>();

		refreshDLM();
		movieJList.setModel(moviesDLM);

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
			refreshDLM();
		});

		deleteButton.addActionListener(e -> {
			int movieIndex = movieJList.getSelectedIndex();
			if (movieIndex >= 0) {
				controllerGUI.deleteMovie(movieJList.getSelectedValue().getId());
				refreshDLM();
			}
		});

		movieJList.addListSelectionListener(e -> {
			int movieIndex = movieJList.getSelectedIndex();
			if (movieIndex >= 0) {
				textFieldTitle.setText(
						movieJList.getSelectedValue().getTitle()
				);
				textFieldAuthor.setText(
						movieJList.getSelectedValue().getAuthor()
				);
				textFieldYear.setText(
						String.valueOf(movieJList.getSelectedValue().getYear())
				);

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
						movieJList.getSelectedValue().getId(),
						textFieldTitle.getText(),
						textFieldAuthor.getText(),
						Integer.parseInt(textFieldYear.getText())
				);
				refreshDLM();
			}
		});

		inMemoryRadioButton.addActionListener(e -> {
			controllerGUI.setStorageType(StorageType.MEM);
			refreshDLM();
		});


		txtRadioButton.addActionListener(e -> {
			controllerGUI.setStorageType(StorageType.TXT);
			refreshDLM();
		});


		mySQLRadioButton.addActionListener(e -> {
			controllerGUI.setStorageType(StorageType.SQL);
			refreshDLM();
		});
	}

	private void refreshDLM() {
		moviesDLM.removeAllElements();
		moviesDLM.addAll(controllerGUI.getMovies());
	}

	public static void main(String[] args) {
		MainGui screen = new MainGui();
		screen.setVisible(true);
	}
}
