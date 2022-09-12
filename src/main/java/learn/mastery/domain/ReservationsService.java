package learn.mastery.domain;

import learn.mastery.data.GuestRepository;
import learn.mastery.data.HostRepository;
import learn.mastery.data.ReservationsRepository;

public class ReservationsService {

    private final ReservationsRepository reservationsRepository;
    private final GuestRepository guestRepository;
    private final HostRepository hostRepository;

    public ReservationsService(ReservationsRepository reservationsRepository, GuestRepository guestRepository, HostRepository hostRepository) {
        this.reservationsRepository = reservationsRepository;
        this.guestRepository = guestRepository;
        this.hostRepository = hostRepository;
    }
}
