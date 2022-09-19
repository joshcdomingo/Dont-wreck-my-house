package learn.mastery.data;

import learn.mastery.models.Guest;
import learn.mastery.models.Reservations;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestFileRepositoryTest {

    private static final String SEED_FILE_PATH = "./data/guests-seed.csv";
    private static final String TEST_FILE_PATH = "./data/guests-test.csv";
    private final GuestFileRepository repository = new GuestFileRepository(TEST_FILE_PATH);
    @Test
    void shouldFindAll() {
        GuestFileRepository repo = new GuestFileRepository("./data/guests.csv");
        List<Guest> all = repo.findAll();
        assertEquals(1000, all.size());
    }

    @Test
    void shouldFindId(){
        Guest actual = repository.findById(1);

        assertNotNull( actual);
    }

}