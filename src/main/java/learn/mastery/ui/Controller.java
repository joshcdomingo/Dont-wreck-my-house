package learn.mastery.ui;

import learn.mastery.data.DataException;
import learn.mastery.domain.GuestService;
import learn.mastery.domain.HostService;
import learn.mastery.domain.ReservationsService;
import learn.mastery.models.Host;
import learn.mastery.models.Reservations;

import java.time.LocalDate;
import java.util.List;

public class Controller {

    private final GuestService guestService;
    private final HostService hostService;
    private final ReservationsService reservationsService;
    private final View view;

    public Controller(GuestService guestService, HostService hostService, ReservationsService reservationsService, View view) {
        this.guestService = guestService;
        this.hostService = hostService;
        this.reservationsService = reservationsService;
        this.view = view;
    }


    public void run() {
        view.displayHeader("Welcome to Dont-Wreck-my-House");
        try {
            runAppLoop();
        } catch (DataException ex) {
            view.displayException(ex);
        }
        view.displayHeader("Goodbye.");
    }

    private void runAppLoop() throws DataException {
        MainMenuOption option;
        do {
            option = view.selectMainMenuOption();
            switch (option) {
                case VIEW_RESERVATIONS:
                    //TODO
                    viewReservationsByHost();
                    break;
                case MAKE_RESERVATIONS:
                    //TODO
                    break;
                case UPDATE_RESERVATIONS:
                    //TODO
                    break;
                case CANCEL_RESERVATIONS:
                    //TODO
                    break;
            }
        } while (option != MainMenuOption.EXIT);
    }

//    private void viewByHost() {
//        String host = view.getHostEmail();
////        List<Reservations> reservations = reservationsService.findByReservations(view.getHostNamePrefix());
//        List<Reservations> reservations = reservationsService.findByReservationsByEmail(host);
//        view.displayReservationsByHost(reservations);
//        view.enterToContinue();
//
////        List<Reservations> reservations = reservationsService.findByReservationsByEmail(view.getHostEmail());
////        view.displayReservationsByHost(reservations);
////        view.enterToContinue();
//    }

    private void viewReservationsByHost() {
        List<Host> hosts = hostService.findByEmail(view.getHostEmail());
        try {
            List<Reservations> reservations = reservationsService.findByReservations(view.getHostInfo(hosts).getHostId());
            view.displayReservationsByHost(reservations);
        }
        catch (NullPointerException exception){
            System.out.println("No Reservation found");
        }
        view.enterToContinue();
    }


}
