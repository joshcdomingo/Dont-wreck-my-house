package learn.mastery.domain;

import learn.mastery.data.GuestFileRepositoryDouble;
import learn.mastery.models.Guest;
import learn.mastery.models.Reservations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class GuestServiceTest {

    GuestService service = new GuestService(new GuestFileRepositoryDouble());

    @Test
    void shouldFindExistingEmail()  {
        List<Guest> all = service.findByEmail(GuestFileRepositoryDouble.GUEST.getEmailAddr());
        for(Guest guest : all){
            assertNotNull(guest.getEmailAddr());
        }
    }

    @Test
    void shouldNotFindBlankEmail()  {
        List<Guest> all = service.findByEmail("");
        for(Guest guest : all){
            assertNull(guest.getEmailAddr());
        }
    }

}