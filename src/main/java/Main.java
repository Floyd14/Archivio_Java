import lombok.extern.log4j.Log4j2;
import view.Menu;

@Log4j2
public class Main {

    public static void main(String[] args) {

//        Movie esempio = new Movie(
//                "The Matrix",
//                "XXX",
//                2000 );

       // Map map = esempio.toMap();

        // 5. Convert to a string
       // System.out.println(map);

//        System.out.println(esempio);
//
//        src.model.Movie esempio2 = new src.model.Movie("The Matrix", "XXX", 2000 );
//        System.out.println(esempio2);
//
//        src.model.Movie esempio3 = new src.model.Movie("The Matrix", "XXX", 2000 );
//        System.out.println(esempio3);
//
//        System.out.println(esempio3.getId());
        log.info("Start Application");
        Menu menu = new Menu();
        menu.showMenu();

    }
}