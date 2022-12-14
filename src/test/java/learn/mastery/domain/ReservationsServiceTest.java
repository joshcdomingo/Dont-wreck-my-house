package learn.mastery.domain;

import learn.mastery.data.DataException;
import learn.mastery.data.GuestFileRepositoryDouble;
import learn.mastery.data.HostFileRepositoryDouble;
import learn.mastery.data.ReservationsRepositoryDouble;
import learn.mastery.models.Host;
import learn.mastery.models.Reservations;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationsServiceTest {
    public final static Host host = new Host("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c","MacTimpany","cmactimpany1u@ebay.com","(203) 7237610","34059 Randy Parkway","Fairfield","CT","06825", BigDecimal.valueOf(476),BigDecimal.valueOf(595));
    ReservationsService service = new ReservationsService(
            new ReservationsRepositoryDouble(),
            new GuestFileRepositoryDouble(),
            new HostFileRepositoryDouble());

    @Test
    void shouldMakeReservation() throws DataException {
        Reservations reservations = new Reservations();
        reservations.setHost(HostFileRepositoryDouble.HOST);

        reservations.setReserveId(0);
        reservations.setGuest(GuestFileRepositoryDouble.GUEST);

        reservations.setStartDate(LocalDate.of(2023, 6, 26));
        reservations.setEndDate(LocalDate.of(2023, 6, 30));
        reservations.setTotal(BigDecimal.valueOf(595));

        Result<Reservations> result = service.add(reservations, host);
        assertTrue(result.isSuccess());
        assertNotNull(result);
    }

    @Test
    void shouldUpdateExistingReservation() throws DataException {
        List<Reservations> all = service.findByReservations(host);
        for(Reservations reservations : all) {
            reservations.setHost(HostFileRepositoryDouble.HOST);


            reservations.setGuest(GuestFileRepositoryDouble.GUEST);

            reservations.setStartDate(LocalDate.of(2023, 5, 30));
            reservations.setEndDate(LocalDate.of(2023, 5, 31));
            reservations.setTotal(BigDecimal.valueOf(595));

            Result<Reservations> result = service.update(reservations, host);
            assertTrue(result.isSuccess());
            assertNotNull(result);
        }
    }

    @Test
    void shouldNotMakeDuplicateReservation() throws DataException {
        List<Reservations> all = service.findByReservations(host);
        Reservations reservations = new Reservations();

        reservations.setGuest(GuestFileRepositoryDouble.GUEST);

        reservations.setHost(host);


        reservations.setStartDate(LocalDate.of(2020, 6, 26));
        reservations.setEndDate(LocalDate.of(2020, 6, 26));
        reservations.setTotal(BigDecimal.valueOf(595));

        Result<Reservations> result = service.add(reservations, host);
        assertFalse(result.isSuccess());

        assertNull(result.getPayload());
        //check to see if it's in the data
        assertFalse(all.contains(result.getPayload()));

        assertEquals("Reservation cannot be the same day!", result.getErrorMessages().get(0));
    }


    @Test
    void shouldNotAddReservationWithSameStartAndEndDate() throws DataException {
        List<Reservations> all = service.findByReservations(host);
        Reservations reservations = new Reservations();

        reservations.setGuest(GuestFileRepositoryDouble.GUEST);

        reservations.setHost(host);


        reservations.setStartDate(LocalDate.of(2020, 6, 26));
        reservations.setEndDate(LocalDate.of(2020, 6, 26));
        reservations.setTotal(BigDecimal.valueOf(595));

        Result<Reservations> result = service.add(reservations, host);
        assertFalse(result.isSuccess());

        assertNull(result.getPayload());
        //check to see if it's in the data
        assertFalse(all.contains(result.getPayload()));

        assertEquals("Reservation cannot be the same day!", result.getErrorMessages().get(0));

    }

    @Test
    void shouldNotAddReservationWithEndDateBeforeStart() throws DataException {
        List<Reservations> all = service.findByReservations(host);
        Reservations reservations = new Reservations();

        reservations.setGuest(GuestFileRepositoryDouble.GUEST);

        reservations.setHost(host);


        reservations.setStartDate(LocalDate.of(2023, 6, 26));
        reservations.setEndDate(LocalDate.of(2023, 6, 24));
        reservations.setTotal(BigDecimal.valueOf(595));

        Result<Reservations> result = service.add(reservations, host);
        assertFalse(result.isSuccess());

        assertNull(result.getPayload());
        //check to see if it's in the data
        assertFalse(all.contains(result.getPayload()));

        assertEquals("Start date cannot be before start date!", result.getErrorMessages().get(0));

    }

    @Test
    void shouldNotAddBeforeCurrentDate() throws DataException {
        List<Reservations> all = service.findByReservations(host);
        Reservations reservations = new Reservations();

        reservations.setGuest(GuestFileRepositoryDouble.GUEST);

        reservations.setHost(host);


        reservations.setStartDate(LocalDate.of(2020, 6, 26));
        reservations.setEndDate(LocalDate.of(2020, 6, 30));
        reservations.setTotal(BigDecimal.valueOf(595));

        Result<Reservations> result = service.add(reservations, host);
        assertFalse(result.isSuccess());

        assertNull(result.getPayload());
        //check to see if it's in the data
        assertFalse(all.contains(result.getPayload()));

        assertEquals("Reservation date cannot be before the current date!", result.getErrorMessages().get(0));

    }


    @Test
    void shouldDeleteExistingReservation() throws DataException {
        Result<Reservations> actual = service.deleteById(1,host);
        assertTrue(actual.isSuccess());
    }

    @Test
    void shouldNotDeleteNonExistingReservation() throws DataException {
        Result<Reservations> actual = service.deleteById(999,host);

        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getErrorMessages().size());
        assertTrue(actual.getErrorMessages().get(0).contains("does not exist"));
    }


}