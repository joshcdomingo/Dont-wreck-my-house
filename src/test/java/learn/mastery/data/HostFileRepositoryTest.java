package learn.mastery.data;

import learn.mastery.models.Guest;
import learn.mastery.models.Host;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HostFileRepositoryTest {
    private static final String SEED_FILE_PATH = "./data/hosts-seed.csv";
    private static final String TEST_FILE_PATH = "./data/hosts-test.csv";
    private final HostFileRepository repository = new HostFileRepository(TEST_FILE_PATH);

    @Test
    void shouldFindAll() {
        HostFileRepository repo = new HostFileRepository("./data/hosts.csv");
        List<Host> all = repo.findAll();
        assertEquals(1000, all.size());
    }

    @Test
    void shouldFindId(){
        Host actual = repository.findById("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c");

        assertNotNull(actual);
        assertEquals("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c",actual.getHostId());
    }

}