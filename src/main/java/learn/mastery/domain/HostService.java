package learn.mastery.domain;
import learn.mastery.data.HostRepository;
import learn.mastery.models.Guest;
import learn.mastery.models.Host;
import learn.mastery.models.Reservations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class HostService {
    private final HostRepository repository;

    public HostService(HostRepository repository) {
        this.repository = repository;
    }


    public List<Host> findByEmail(String email) {
        return repository.findAll().stream()
                .filter(i -> i.getEmailAddr().matches(email))
                .collect(Collectors.toList());
    }

}
