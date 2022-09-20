package learn.mastery.domain;

import learn.mastery.data.GuestRepository;
import learn.mastery.models.Guest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestService {
    private final GuestRepository repository;

    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    public List<Guest> findByEmail(String email) {
        return repository.findAll().stream()
                .filter(i -> i.getEmailAddr().matches(email))
                .collect(Collectors.toList());
    }

}
