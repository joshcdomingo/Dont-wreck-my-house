package learn.mastery.domain;

import learn.mastery.data.GuestRepository;
import learn.mastery.data.HostRepository;
import learn.mastery.data.ReservationsRepository;
import learn.mastery.models.Guest;
import learn.mastery.models.Host;
import learn.mastery.models.Reservations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class ReservationsService {

    private final ReservationsRepository reservationsRepository;
    private final GuestRepository guestRepository;
    private final HostRepository hostRepository;

    public ReservationsService(ReservationsRepository reservationsRepository, GuestRepository guestRepository, HostRepository hostRepository) {
        this.reservationsRepository = reservationsRepository;
        this.guestRepository = guestRepository;
        this.hostRepository = hostRepository;
    }
    public List<Reservations> findByReservations(String hostsId) {

        Map<Integer, Guest> guestMap = guestRepository.findAll().stream()
                .collect(Collectors.toMap(Guest::getGuestId, i -> i));

        Map<String, Host> hostMap = hostRepository.findAll().stream()
                .collect(Collectors.toMap(Host::getHostId, i -> i));

        List<Reservations> result = reservationsRepository.findByReservations(hostsId);
        for (Reservations reservations1 : result) {
            reservations1.setGuest(guestMap.get(reservations1.getGuest().getGuestId()));
            reservations1.setHost(hostMap.get(reservations1.getHost().getHostId()));
        }
        return result;
    }

//    private Result<Reservations> validate(Reservations reservations,String hostsId) {
//        List<Reservations> forages = reservationsRepository.findByReservations(hostsId);
//
//        Result<Reservations> result = validateNulls(forage);
//
//
//
//        if (!result.isSuccess()) {
//            return result;
//        }
//
//        //check for duplicate item,forager,date
//        for(Forage forage1 : forages) {
//            if(forage1.getForager().getId().matches(forage.getForager().getId()) &&
//                    forage1.getItem().getId() == forage.getItem().getId() &&
//                    forage1.getDate().equals(forage.getDate())){
//                result.addErrorMessage("Duplicate Forage");
//                return result;
//            }
//        }
//
//        private Result<Reservations> validateNulls(Reservations reservations) {
//            Result<Reservations> result = new Result<>();
//
//            if (reservations == null) {
//                result.addErrorMessage("Nothing to save.");
//                return result;
//            }
//
//            if (forage.getDate() == null) {
//                result.addErrorMessage("Forage date is required.");
//            }
//
//            if (forage.getForager() == null) {
//                result.addErrorMessage("Forager is required.");
//            }
//
//            if (forage.getItem() == null) {
//                result.addErrorMessage("Item is required.");
//            }
//            return result;
//        }

}
