package learn.mastery.models;

import java.time.LocalDate;

public class Reservations {
    private int reserveId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int guestId;
    private double total;

    public Reservations() {
    }

    public Reservations(int reserveId, LocalDate startDate, LocalDate endDate, int guestId, double total) {
        this.reserveId = reserveId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestId = guestId;
        this.total = total;
    }

    public int getReserveId() {
        return reserveId;
    }

    public void setReserveId(int reserveId) {
        this.reserveId = reserveId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
