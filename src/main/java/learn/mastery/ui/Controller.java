package learn.mastery.ui;

import learn.mastery.data.DataException;
import learn.mastery.domain.GuestService;
import learn.mastery.domain.HostService;
import learn.mastery.domain.ReservationsService;
import learn.mastery.domain.Result;
import learn.mastery.models.Guest;
import learn.mastery.models.Host;
import learn.mastery.models.Reservations;

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
                    viewReservationsByHost();
                    break;
                case MAKE_RESERVATIONS:
                    addReservation();
                    break;
                case UPDATE_RESERVATIONS:
                    updateEntry();
                    break;
                case CANCEL_RESERVATIONS:
                    deleteReservation();
                    break;
            }
        } while (option != MainMenuOption.EXIT);
    }

    //VIEW
    private void viewReservationsByHost() {
        view.displayHeader(MainMenuOption.VIEW_RESERVATIONS.getMessage());
        List<Host> hosts = hostService.findByEmail(view.getHostEmail());
        Host host = view.getHostInfo(hosts);
        try {
            List<Reservations> reservations = reservationsService.findByReservations(host);
            view.displayReservationsByHost(reservations);
        }
        catch (NullPointerException exception){
            System.out.println("No Reservation found");
        }
        view.enterToContinue();
    }

    //CREATE
    private void addReservation() throws DataException {
        view.displayHeader(MainMenuOption.MAKE_RESERVATIONS.getMessage());
        List<Guest> guests = guestService.findByEmail(view.getGuestEmail());
        List<Host> hosts = hostService.findByEmail(view.getHostEmail());
        Guest guest = view.getGuestInfo(guests);
        if(guest == null){
            return;
        }
        Host host = view.getHostInfo(hosts);
        if(hosts == null){
            return;
        }
        List<Reservations> reservations = reservationsService.findByReservations(host);
        view.displayReservationsByHost(reservations);
        Reservations reservations1 = view.makeReservation(guest,host);
        if(reservations1 != null) {
            Result<Reservations> result = reservationsService.add(reservations1, host);
            if (!result.isSuccess()) {
                view.displayStatus(false, result.getErrorMessages());
            } else if (result.isSuccess()) {
                String successMessage = String.format("Reservation %s created.", result.getPayload().getReserveId());
                view.displayStatus(true, successMessage);
            }
        }
        else{
            view.displayStatus(false, "Reservation not made");
        }

    }

    // UPDATE
    private void updateEntry() throws DataException {
        view.displayHeader(MainMenuOption.UPDATE_RESERVATIONS.getMessage());
        List<Guest> guests = guestService.findByEmail(view.getGuestEmail());
        List<Host> hosts = hostService.findByEmail(view.getHostEmail());
        Guest guest = view.getGuestInfo(guests);

        if(guest == null){
            return;
        }
        Host host = view.getHostInfo(hosts);
        if(host == null){
            return;
        }
        List<Reservations> reservations = reservationsService.findByReservations(host);
        view.displayOneReservation(reservations);
        Reservations reservations1 = reservationsService.findById(view.updateById(),host);
        if(reservations != null) {
            Reservations updatedReserve = view.makeReservation(guest,host);
            updatedReserve.setReserveId(reservations1.getReserveId());
            Result<Reservations> result = reservationsService.update(updatedReserve,host);
            if(!result.isSuccess()){
                view.displayStatus(false, result.getErrorMessages());
            }
            else if(result.isSuccess()){
                String successMessage = String.format("Reservation was updated!");
                view.displayStatus(true, successMessage);
            }
        }
        else{
            view.displayStatus(false, "Reservation not updated");
        }
    }

    //DELETE
    private void deleteReservation() throws DataException {
        view.displayHeader(MainMenuOption.CANCEL_RESERVATIONS.getMessage());
        List<Guest> guests = guestService.findByEmail(view.getGuestEmail());
        List<Host> hosts = hostService.findByEmail(view.getHostEmail());
        Guest guest = view.getGuestInfo(guests);

        if(guest == null){
            return;
        }
        Host host = view.getHostInfo(hosts);
        if(host == null){
            return;
        }
        List<Reservations> reservations = reservationsService.findByReservations(host);
        view.displayOneReservation(reservations);
        Reservations reservations1 = reservationsService.findById(view.updateById(),host);

        if(reservations != null) {
            Result<Reservations> result = reservationsService.deleteById(reservations1.getReserveId(),host);
            if(!result.isSuccess()){
                view.displayStatus(false, result.getErrorMessages());
            }
            else if(result.isSuccess()){
                String successMessage = "Reservation was deleted";
                view.displayStatus(true, successMessage);
            }
        }
    }

//    private Guest getGuest() {
//        String email = view.getGuestEmail();
//        List<Guest> guests = guestService.findByEmail(email);
//        return view.getGuest(guests);
//    }
//
//    private Host getHost() {
//        String email = view.getHostEmail();
//        List<Host> hosts = hostService.findByEmail(email);
//        return view.getHost(hosts);
//    }



}