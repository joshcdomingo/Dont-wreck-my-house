package learn.mastery.data;

import learn.mastery.models.Guest;
import learn.mastery.models.Host;

import java.util.ArrayList;
import java.util.List;

public class GuestFileRepositoryDouble implements GuestRepository{
    public final static Guest GUEST = new Guest(1000,"Carrissa","Bracher","cbracherrr@ihg.com","(414) 1209230","WI");
    private final ArrayList<Guest> guests = new ArrayList<>();

    public GuestFileRepositoryDouble() {
        guests.add(GUEST);
    }
    @Override
    public List<Guest> findAll() {
        return guests;
    }

    @Override
    public Guest findById(int id) {
            return guests.stream()
                    .filter(i -> i.getGuestId()== id)
                    .findFirst()
                    .orElse(null);
    }
}
