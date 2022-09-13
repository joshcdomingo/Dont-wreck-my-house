package learn.mastery.ui;

import learn.mastery.models.Host;
import learn.mastery.models.Reservations;

import java.util.List;
import java.util.stream.Collectors;

public class View {

    private Host hostInfo;
    private final ConsoleIO io;

    public View(ConsoleIO io) {
        this.io = io;
    }

    public String getHostEmail() {
        return io.readRequiredString("Hosts Email Address: ");
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
                    reservations1.getStartDate(),
                    reservations1.getEndDate(),
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


}
