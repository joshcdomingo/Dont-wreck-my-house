package learn.mastery;

import learn.mastery.data.GuestFileRepository;
import learn.mastery.data.HostFileRepository;
import learn.mastery.data.HostRepository;
import learn.mastery.data.ReservationsFileRepository;
import learn.mastery.domain.GuestService;
import learn.mastery.domain.HostService;
import learn.mastery.domain.ReservationsService;
import learn.mastery.ui.ConsoleIO;
import learn.mastery.ui.Controller;
import learn.mastery.ui.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan
@PropertySource("classpath:data.properties")
public class App {

    public static void main(String[] args) {
        configureWithXMLandRun();
    }

    // with xml
    private static void configureWithXMLandRun(){
        ApplicationContext container = new ClassPathXmlApplicationContext("dependency-config.xml");
        Controller controller = container.getBean(Controller.class);
        controller.run();
    }

    // with annotations
    private static void configureWithAnnotationsAndRun(){
        ApplicationContext container = new AnnotationConfigApplicationContext(App.class);
        // this stays the same
        Controller controller = container.getBean(Controller.class);
        controller.run();
    }

    private static void configureManuallyandRun(){
        ConsoleIO io = new ConsoleIO();
        View view = new View(io);

        ReservationsFileRepository reservationsFileRepository = new ReservationsFileRepository("./data/reservations");
        HostFileRepository hostFileRepository = new HostFileRepository("./data/hosts.csv");
        GuestFileRepository guestFileRepository = new GuestFileRepository("./data/guests.csv");

        ReservationsService reservationsService = new ReservationsService(reservationsFileRepository, guestFileRepository, hostFileRepository);
        HostService hostService = new HostService(hostFileRepository);
        GuestService guestService = new GuestService(guestFileRepository);

        Controller controller = new Controller(guestService, hostService, reservationsService, view);
        controller.run();

    }
}
