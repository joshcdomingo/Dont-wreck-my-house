package learn.mastery.ui;

import learn.mastery.models.Guest;
import learn.mastery.models.Host;
import learn.mastery.models.Reservations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class View {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private Host hostInfo;

    private Guest guestInfo;
    private final ConsoleIO io;

    public View(ConsoleIO io) {
        this.io = io;
    }

    public String getHostEmail() {
        return io.readRequiredString("Hosts Email Address: ");
    }
    public String getGuestEmail() {
        return io.readRequiredString("Guest Email Address: ");
    }
    public void enterToContinue() {
        io.readString("Press [Enter] to continue.");
    }

    public MainMenuOption selectMainMenuOption() {
        displayHeader("Main Menu");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (MainMenuOption option : MainMenuOption.values()) {
            if (!option.isHidden()) {
                io.printf("%s. %s%n", option.getValue(), option.getMessage());
            }
            min = Math.min(min, option.getValue());
            max = Math.max(max, option.getValue());
        }

        String message = String.format("Select [%s-%s]: ", min, max - 1);
        return MainMenuOption.fromValue(io.readInt(message, min, max));
    }

    public void displayHeader(String message) {
        io.println("");
        io.println(message);
        io.println("=".repeat(message.length()));
    }

    public void displayException(Exception ex) {
        displayHeader("A critical error occurred:");
        io.println(ex.getMessage());
    }

    public Reservations makeReservation(Guest guest, Host hosts) {
        Reservations reserve = new Reservations();
        reserve.setGuest(guest);
        reserve.setHost(hosts);
        reserve.setStartDate(io.readLocalDate("Enter a check-in date [MM/dd/yyyy]: "));
        reserve.setEndDate(io.readLocalDate("Enter a check-out date [MM/dd/yyyy]: "));
        int daysIn = reserve.getEndDate().getDayOfMonth() - reserve.getStartDate().getDayOfMonth();
        double rate = hostInfo.getStandRate()*daysIn;
        reserve.setTotal(rate);
        System.out.println("Total price of stay: ");
        if(confirmPrice()){
            return reserve;
        }
        else{
            return null;
        }
    }

    public boolean confirmPrice(){
        return io.readBoolean("Confirm [y/n]:");
    }

    public void displayReservationsByHost(List<Reservations> reservations) {
        if (reservations == null || reservations.isEmpty()) {
            io.println("No reservations found.");
            return;
        }


        System.out.println();
        System.out.printf("%s: %s, %s%n",hostInfo.getLastName(),hostInfo.getCity(),hostInfo.getState());
        System.out.println("=".repeat(30));
        for (Reservations reservations1 : reservations) {
            io.printf("ID: %s, %s - %s, Guest:%s, %s, Email: %s%n",
                    reservations1.getReserveId(),
                    formatter.format(reservations1.getStartDate()),
                    formatter.format(reservations1.getEndDate()),
                    reservations1.getGuest().getFirstName(),
                    reservations1.getGuest().getLastName(),
                    reservations1.getGuest().getEmailAddr()
            );
        }
    }

    public String getReservationEmail() {
        displayHeader(MainMenuOption.VIEW_RESERVATIONS.getMessage());
        return io.readRequiredString("Enter the Host Email: ");
    }

    public Host getHostInfo(List<Host> hosts) {
        if (hosts == null || hosts.isEmpty()) {
            io.println("No hosts found.");
            return null;
        }

        for (Host host : hosts) {
            hostInfo = host;
        }
            return hostInfo;

    }
//    public Guest getGuest(List<Guest> guests){
//        String email = io.readRequiredString("Enter Guest Email:");
//        Guest guest = guests.stream().filter(guest1 -> Objects.equals(guest1.getEmailAddr(), email)).findFirst().orElse(null);
//
//        if(guest == null){
//            displayStatus(false, String.format("No guests found!"));
//        }
//        return guest;
//    }
//
//    public Host getHost(List<Host> hosts){
//        String email = io.readRequiredString("Enter Host Email:");
//        Host host = hosts.stream().filter(host1 -> Objects.equals(host1.getEmailAddr(), email)).findFirst().orElse(null);
//
//        if(host == null){
//            displayStatus(false, String.format("No hosts found!"));
//        }
//        return host;
//    }

    public Guest getGuestInfo(List<Guest> guests) {
        if (guests == null || guests.isEmpty()) {
            io.println("No guests found.");
            return null;
        }

        for (Guest guest : guests) {
            guestInfo = guest;
        }
        return guestInfo;
    }


    public void displayStatus(boolean success, String message) {
        displayStatus(success, List.of(message));
    }

    public void displayStatus(boolean success, List<String> messages) {
        displayHeader(success ? "Success" : "Error");
        for (String message : messages) {
            io.println(message);
        }
    }


}
