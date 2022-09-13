package learn.mastery.ui;

import learn.mastery.models.Guest;
import learn.mastery.models.Host;
import learn.mastery.models.Reservations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class View {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private Host hostInfo;

    private Guest guestInfo;
    private Reservations reserveInfo;
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

    public Reservations makeReservation(String hostsId) {
        Reservations reserve = new Reservations();
        reserve.setGuest(guestInfo);
        reserve.setStartDate(io.readLocalDate("Enter a check-in date [MM/dd/yyyy]: "));
        reserve.setEndDate(io.readLocalDate("Enter a check-out date [MM/dd/yyyy]: "));
        int daysIn = reserve.getEndDate().getDayOfMonth() - reserve.getStartDate().getDayOfMonth();
        double rate = hostInfo.getStandRate()*daysIn;
        reserve.setTotal(rate);
        System.out.println("Total price of stay: ");
        return reserve;
    }

    public boolean confirmPrice(){
        return io.readBoolean("Confirm [y/n]:");
    }

//    public Host chooseHost(List<Host> hosts) {
//        if (hosts.size() == 0) {
//            io.println("No hosts found");
//            return null;
//        }
//        int index = 1;
//        for (Host host : hosts.stream().limit(25).collect(Collectors.toList())) {
//            io.printf("%s: %s %s%n", index++, host.getEmailAddr(), host.getLastName());
//        }
//        index--;
//
//        if (hosts.size() > 25) {
//            io.println("More than 25 foragers found. Showing first 25. Please refine your search.");
//        }
//        io.println("0: Exit");
//        String message = String.format("Select a forager by their index [0-%s]: ", index);
//
//        index = io.readInt(message, 0, index);
//        if (index <= 0) {
//            return null;
//        }
//        return hosts.get(index - 1);
//    }
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
    public Guest getGuest(List<Guest> guests){
        String email = io.readRequiredString("Enter Guest Email:");
        Guest guest = guests.stream().filter(guest1 -> guest1.getEmailAddr() == email).findFirst().orElse(null);

        if(guest == null){
            displayStatus(false, String.format("No guests found!"));
        }
        return guest;
    }

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

    public Reservations getReserveinfo(List<Reservations> reservations) {
        if (reservations == null || reservations.isEmpty()) {
            io.println("No guests found.");
            return null;
        }

        for (Reservations reservations1 : reservations) {
            reserveInfo = reservations1;
        }
        return reserveInfo;
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
