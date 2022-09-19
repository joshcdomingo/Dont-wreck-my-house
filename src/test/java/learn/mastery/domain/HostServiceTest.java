package learn.mastery.domain;

import learn.mastery.data.GuestFileRepositoryDouble;
import learn.mastery.data.HostFileRepository;
import learn.mastery.data.HostFileRepositoryDouble;
import learn.mastery.models.Guest;
import learn.mastery.models.Host;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HostServiceTest {
    HostService service = new HostService(new HostFileRepositoryDouble());

    @Test
    void shouldFindExistingEmail()  {
        List<Host> all = service.findByEmail(HostFileRepositoryDouble.HOST.getEmailAddr());
        for(Host host : all){
            assertNotNull(host.getEmailAddr());
        }
    }

    @Test
    void shouldNotFindBlankEmail()  {
        List<Host> all = service.findByEmail("");
        for(Host host : all){
            assertNull(host.getEmailAddr());
        }
    }

}