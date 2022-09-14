package learn.mastery.data;

import learn.mastery.models.Guest;
import learn.mastery.models.Host;
import learn.mastery.models.Reservations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class ReservationsFileRepositoryTest {

    static final String SEED_FILE_PATH = "./data/reservation-seed-d59368be-f087-4f61-91bb-1bfc9b076f97.csv";
    static final String TEST_FILE_PATH = "./data/reservation_data_test/d59368be-f087-4f61-91bb-1bfc9b076f97.csv";
    static final String TEST_DIR_PATH = "./data/reservation_data_test";
    ReservationsFileRepository repository = new ReservationsFileRepository(TEST_DIR_PATH);

    final Host host = new Host("d59368be-f087-4f61-91bb-1bfc9b076f97","Maddick","vmaddick1v@tiny.cc","(770) 6214581","63621 Bonner Point","Gainesville","GA",30506,329,411.25);


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

        Host host = new Host();
        reservations.setHost(host);


        reservations.setStartDate(LocalDate.of(2020, 6, 26));
        reservations.setEndDate(LocalDate.of(2020, 6, 30));

        reservations.setTotal(BigDecimal.valueOf(reservations.getEndDate().getDayOfYear()-reservations.getStartDate().getDayOfYear()));



        reservations = repository.add(reservations);

        assertNotNull(reservations);
    }

}