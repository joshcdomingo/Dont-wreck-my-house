package learn.mastery.data;

import learn.mastery.models.Host;
import learn.mastery.models.Reservations;

import java.util.List;

public interface ReservationsRepository {

    Reservations add(Reservations reservations) throws DataException;

    List<Reservations> findByReservations(String reservations);

    List<Reservations> findByReservationsByEmail(Host reservations);

    List<Reservations> findByReservationsByEmail(String email);
}
