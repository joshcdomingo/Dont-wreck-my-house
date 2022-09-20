package learn.mastery.data;

import learn.mastery.models.Host;
import learn.mastery.models.Reservations;

import java.util.List;

public interface ReservationsRepository {


    //CREATE
    Reservations add(Reservations reservations) throws DataException;

    //UPDATE
    boolean update(Reservations reservations, Host host) throws DataException;


    List<Reservations> findByReservations(Host host);

    boolean deleteById(int reserveId, Host host) throws  DataException;

    Reservations findById(int reservationsId, Host host) throws DataException;
}
