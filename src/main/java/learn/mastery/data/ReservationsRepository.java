package learn.mastery.data;

import learn.mastery.models.Reservations;

import java.util.List;

public interface ReservationsRepository {
    List<Reservations> findByReservations(Reservations reservations);
}
