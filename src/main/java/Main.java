import lombok.extern.log4j.Log4j2;
import view.Menu;

@Log4j2
public class Main {

    public static void main(String[] args) {

//        Movie esempio = new Movie(
//                "The Matrix",
//                "XXX",
//                2000 );

        log.info("Start Application");
        Menu menu = new Menu();
        menu.showMenu();

    }
}
