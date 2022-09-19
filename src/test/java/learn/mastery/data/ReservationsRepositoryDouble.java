package learn.mastery.data;

import learn.mastery.models.Guest;
import learn.mastery.models.Host;
import learn.mastery.models.Reservations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationsRepositoryDouble implements ReservationsRepository{

    public final static Host host = new Host("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c","MacTimpany","cmactimpany1u@ebay.com","(203) 7237610","34059 Randy Parkway","Fairfield","CT","06825", BigDecimal.valueOf(476),BigDecimal.valueOf(595));
    private final ArrayList<Reservations> reservations2 = new ArrayList<>();

    public ReservationsRepositoryDouble(){
        Reservations reservations1 = new Reservations();
        reservations1.setHost(host);
        reservations1.setReserveId(1);
        reservations1.setStartDate(LocalDate.of(2022, 10, 21));
        reservations1.setEndDate(LocalDate.of(2022, 10, 27));
        reservations1.setGuest(GuestFileRepositoryDouble.GUEST);
        reservations1.setTotal(BigDecimal.valueOf(400.0));
        reservations2.add(reservations1);
    }


    @Override
    public boolean update(Reservations reservations, Host host) throws DataException {
        return reservations.getReserveId() > 0;
    }

    @Override
    public Reservations add(Reservations reservations) throws DataException {
        reservations.setHost(host);
        reservations2.add(reservations);
        return reservations;
    }

    @Override
    public List<Reservations> findByReservations(Host host) {
        return reservations2.stream()
                .filter(i -> i.getHost().equals(host))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(int reserveId, Host host) throws DataException {
        return reserveId != 999;
    }

    @Override
    public Reservations findById(int reservationsId, Host host) throws DataException {
        return null;
    }
}
