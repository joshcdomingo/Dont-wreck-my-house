package learn.mastery.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Reservations {
    private int reserveId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Guest guest;
    private Host host;
    private BigDecimal total;

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

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getValue() {
        return getStandardRate().add(getWeekendRate());
    }

    public BigDecimal getWeekendRate() {
        long daysDiff = ChronoUnit.DAYS.between(getStartDate(), getEndDate());
        long weekDays = grabWeekdays(getStartDate(), getEndDate());
        long weekEnds = daysDiff - weekDays;
        return host.getWeekRate().multiply(BigDecimal.valueOf(weekEnds));

    }

    public long getTotalDays() {
        return ChronoUnit.DAYS.between(getStartDate(), getEndDate());
    }
    public BigDecimal getStandardRate() {
        long weekDays = grabWeekdays(getStartDate(), getEndDate());
        return host.getStandRate().multiply(BigDecimal.valueOf(weekDays));
    }

    public long grabWeekdays(LocalDate start, LocalDate end) {
        //acquire the starting day of the week
        DayOfWeek startW = start.getDayOfWeek();
        //acquire the ending day of the week
        DayOfWeek endW = end.getDayOfWeek();

        //represents a standard set of date periods
        long days = ChronoUnit.DAYS.between(start, end);
        //this is where i grab the days without weekends
        long daysWithoutWeekends = days - 2 * ((days + startW.getValue())/7);

        //return the days without weekends combined with the start and end day of the week
        return daysWithoutWeekends + (startW == DayOfWeek.MONDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);
    }

}
