import java.util.Scanner;
public class Menu {
    private final Controller ctr = new Controller();
    private final Scanner scanner = new Scanner(System.in);

    private final String MENU_PROMPT;
    {
        MENU_PROMPT = """
                        Scegli l'opzione corrispodente:
                        - a: per aggiungere un film in archivio
                        - l: per listare i film in archivio
                        - d: per eliminare un film
                        - q: per uscire
                        """;
    }
    public void showMenu(){
        String input = "";
        while (!input.equalsIgnoreCase("q")) {
            System.out.print(MENU_PROMPT);
            input = this.scanner.nextLine();
            switch (input) {
                case "a" -> this.addMovie();
                case "l" -> this.listMovies();
                case "d" -> this.deleteMovie();
                default -> System.out.println("Input non valido \n");
            }
        }
    }

    private void listMovies() {
        ctr.listMovies();
    }

    private void addMovie() {
        System.out.print("Titolo:");
        String titolo = this.scanner.nextLine().trim();

        System.out.print("Autore:");
        String autore = this.scanner.nextLine().trim();

        System.out.print("Anno:");
        int anno = Integer.parseInt(this.scanner.nextLine().trim());

        ctr.addMovie(titolo, autore, anno);
        showMenu();
    }

    private void deleteMovie() {
        ctr.listMovies();
        System.out.print("ID da eliminare:");
        String id = this.scanner.nextLine().trim();
    }
}
