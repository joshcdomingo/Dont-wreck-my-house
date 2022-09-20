package learn.mastery.data;

import learn.mastery.models.Guest;
import learn.mastery.models.Host;
import learn.mastery.models.Reservations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ReservationsFileRepositoryTest {

    static final String SEED_FILE_PATH = "./data/reservation-seed-d59368be-f087-4f61-91bb-1bfc9b076f97.csv";
    static final String TEST_FILE_PATH = "./data/reservation_data_test/d59368be-f087-4f61-91bb-1bfc9b076f97.csv";
    static final String TEST_DIR_PATH = "./data/reservation_data_test";
    ReservationsFileRepository repository = new ReservationsFileRepository(TEST_DIR_PATH);
    static final int FORAGE_COUNT = 17;

    final Host host = new Host("d59368be-f087-4f61-91bb-1bfc9b076f97", "Maddick", "vmaddick1v@tiny.cc", "(770) 6214581", "63621 Bonner Point", "Gainesville", "GA", "30506", new BigDecimal(329), new BigDecimal(411.25));


    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldAdd() throws DataException {
        Reservations reservations = new Reservations();
        reservations.setHost(host);

        Guest guest = new Guest();
        reservations.setGuest(guest);


        reservations.setStartDate(LocalDate.of(2020, 6, 26));
        reservations.setEndDate(LocalDate.of(2020, 6, 30));

        reservations.setTotal(BigDecimal.valueOf((reservations.getEndDate().getDayOfYear() - reservations.getStartDate().getDayOfYear())).multiply(host.getStandRate()));


        reservations = repository.add(reservations);

        assertNotNull(reservations);
    }

    @Test
    void shouldDelete() throws DataException {
        boolean result = repository.deleteById(1,host);
        Assertions.assertTrue(result);
        List<Reservations> all = repository.findByReservations(host);
        Assertions.assertEquals(16,all.size());
        Reservations reservations = repository.findById(1,host);
        Assertions.assertNull(reservations);
    }

    @Test
    public void shouldUpdate() throws DataException {
        Reservations reservations = repository.findById(1,host);
        reservations.setHost(host);

        Guest guest = new Guest();
        reservations.setGuest(guest);

        reservations.setHost(host);


        reservations.setStartDate(LocalDate.of(2020, 6, 26));
        reservations.setEndDate(LocalDate.of(2020, 6, 30));
        boolean result = this.repository.update(reservations, host);
        Assertions.assertTrue(result);
        reservations = repository.findById(1, host);
        Assertions.assertNotNull(reservations);
        Assertions.assertEquals(1, reservations.getReserveId());
        Assertions.assertEquals(LocalDate.of(2020,06,26), reservations.getStartDate());
        Assertions.assertEquals(LocalDate.of(2020,06,30), reservations.getEndDate());
        Assertions.assertEquals(0, reservations.getGuest().getGuestId());
        Assertions.assertEquals(BigDecimal.valueOf(1579.5),reservations.getTotal());
    }
    @Test
    public void shouldFindByReservation(){
        List<Reservations> reservations = repository.findByReservations(host);
        assertEquals(FORAGE_COUNT, reservations.size());
    }

}