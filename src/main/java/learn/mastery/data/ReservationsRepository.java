package learn.mastery.data;

import learn.mastery.models.Host;
import learn.mastery.models.Reservations;

import java.util.List;

public interface ReservationsRepository {

    //UPDATE
    boolean update(Reservations reservations, String hostsId) throws DataException;

    Reservations add(Reservations reservations) throws DataException;

    List<Reservations> findByReservations(String reservations);

    boolean deleteById(int reserveId, String hostsId) throws  DataException;

    Reservations findById(int reservationsId, String hostsId) throws DataException;
}
