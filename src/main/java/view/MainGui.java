package view;

import controller.Controller;
import model.Movie;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.List;

public class MainGui extends JFrame {
    private JList listMovies;
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
    private Controller controllerGUI;
    private List<Movie> movieList;
    private DefaultListModel movies;

    MainGui() {
        super("Archivio");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
        //------------------------------
        this.controllerGUI = new Controller();
        this.movies = new DefaultListModel();
        listMovies.setModel(movies);
        refreshScreenList();
        //------------------------------


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGUI.addMovie(
                        textFieldTitle.getText(),
                        textFieldAuthor.getText(),
                        Integer.parseInt(textFieldYear.getText())
                );
                refreshScreenList();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int movieIndex = listMovies.getSelectedIndex();
                if( movieIndex >= 0) {
                    controllerGUI.deleteMovie(movieIndex);
                    refreshScreenList();
                }
            }
        });
        listMovies.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int movieIndex = listMovies.getSelectedIndex();
                if (movieIndex >= 0) {
                    Movie movie = movieList.get(movieIndex);
                    textFieldTitle.setText(movie.getTitle());
                    textFieldAuthor.setText(movie.getAuthor());
                    textFieldYear.setText(String.valueOf(movie.getYear()));

                    updateButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                } else {
                    updateButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int movieIndex = listMovies.getSelectedIndex();
                if( movieIndex >= 0) {
                    controllerGUI.updateMovie(
                            movieIndex,
                            textFieldTitle.getText(),
                            textFieldAuthor.getText(),
                            Integer.parseInt(textFieldYear.getText())
                            );
                    refreshScreenList();
                }
            }
        });


        ItemListener listener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        };
        inMemoryRadioButton.addItemListener(listener);
        txtRadioButton.addItemListener(listener);
        mySQLRadioButton.addItemListener(listener);
    }

    private void refreshScreenList() {
        movieList = controllerGUI.getMovies();
        movies.removeAllElements();
        for (Movie movie : movieList) {
            movies.addElement(movie.getTitle());
        }
    }

    public static void main(String[] args) {
        MainGui screen = new MainGui();
        screen.setVisible(true);
    }
}
