package learn.mastery.domain;

import learn.mastery.data.DataException;
import learn.mastery.data.GuestRepository;
import learn.mastery.data.HostRepository;
import learn.mastery.data.ReservationsRepository;
import learn.mastery.models.Guest;
import learn.mastery.models.Host;
import learn.mastery.models.Reservations;
import org.springframework.stereotype.Service;

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

    public Result<Reservations> add(Reservations reserve, String hostsId) throws DataException {
        Result<Reservations> result = validate(reserve, hostsId);
        if (!result.isSuccess()) {
            return result;
        }


        result.setPayload(reservationsRepository.add(reserve));

        return result;
    }

    private Result<Reservations> validate(Reservations reserve,String hostsId) {
        List<Reservations> reservations = reservationsRepository.findByReservations(hostsId);

        Result<Reservations> result = validateNulls(reserve);


        if (!result.isSuccess()) {
            return result;
        }

//        //check for duplicate reservation
//        for(Reservations reservations1 : reservations) {
//            if(reservations1.getGuest().getEmailAddr().equals(reserve.getGuest().getEmailAddr()) &&
//                    reservations1.getHost().getEmailAddr().equals(reserve.getHost().getEmailAddr()) &&
//                    reservations1.getStartDate().equals(reserve.getStartDate()) &&
//                    reservations1.getEndDate().equals(reserve.getEndDate())){
//                result.addErrorMessage("Reservation is taken already!");
//                return result;
//            }
//        }

        validateFields(reserve, result);
        if (!result.isSuccess()) {
            return result;
        }

        validateChildrenExist(reserve, result);

        return result;
    }

        private Result<Reservations> validateNulls(Reservations reservations) {
            Result<Reservations> result = new Result<>();

            if (reservations == null) {
                result.addErrorMessage("Nothing to save");
                return result;
            }

            if (reservations.getHost().getHostId() == null) {
                result.addErrorMessage("Invalid reservation ID");
            }

            if (reservations.getHost() == null) {
                result.addErrorMessage("Host required");
            }

            if (reservations.getGuest() == null) {
                result.addErrorMessage("Guest required");
            }
            return result;
        }

    private void validateFields(Reservations reservations, Result<Reservations> result) {
        // No past dates
        if (reservations.getEndDate().isBefore(reservations.getStartDate())) {
            result.addErrorMessage("Start date cannot be before start date!");
        }

    }

    public Result<Reservations> deleteById(int reserveId, String hostsId) throws DataException {
       Result<Reservations> result = new Result<>();
        if(!reservationsRepository.deleteById(reserveId, hostsId)){
            result.addErrorMessage("Reservation ID does not exist!");
        }
        return result;
    }

    //UPDATE
    public Result<Reservations> update(Reservations reservations, String hostsId) throws DataException {
        Result<Reservations> result = validate(reservations, hostsId);
        if(!result.isSuccess()){
            return result; // we can't go further if the result fails
        }
        boolean updated = reservationsRepository.update(reservations,hostsId);

        if(!updated){
            result.addErrorMessage(String.format("Reservation with id %s does not exist", reservations.getReserveId()));
        }

        return result;
    }

    public Reservations findById(int reservationId, String hostsId) throws DataException {
        return reservationsRepository.findById(reservationId,hostsId);
    }

    private void validateChildrenExist(Reservations reservations, Result<Reservations> result) {

        if (reservations.getHost().getHostId() == null
                || hostRepository.findById(reservations.getHost().getHostId()) == null) {
            result.addErrorMessage("Host does not exist.");
        }

        if (guestRepository.findById(reservations.getGuest().getGuestId()) == null) {
            result.addErrorMessage("Guest does not exist.");
        }
    }

}
