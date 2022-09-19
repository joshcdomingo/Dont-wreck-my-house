package learn.mastery.domain;

import learn.mastery.data.DataException;
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
    public List<Reservations> findByReservations(Host host) {

        Map<Integer, Guest> guestMap = guestRepository.findAll().stream()
                .collect(Collectors.toMap(Guest::getGuestId, i -> i));

        Map<String, Host> hostMap = hostRepository.findAll().stream()
                .collect(Collectors.toMap(Host::getHostId, i -> i));

        List<Reservations> result = reservationsRepository.findByReservations(host);
        for (Reservations reservations1 : result) {
            reservations1.setGuest(guestMap.get(reservations1.getGuest().getGuestId()));
            reservations1.setHost(hostMap.get(reservations1.getHost().getHostId()));
        }
        return result;
    }

    public Result<Reservations> add(Reservations reserve, Host host) throws DataException {
        Result<Reservations> result = validate(reserve, host);
        if (!result.isSuccess()) {
            return result;
        }


        result.setPayload(reservationsRepository.add(reserve));

        return result;
    }

    private Result<Reservations> validate(Reservations reserve,Host host) {
        List<Reservations> reservations = reservationsRepository.findByReservations(host);

        Result<Reservations> result = validateNulls(reserve);


        if (!result.isSuccess()) {
            return result;
        }

        //check for duplicate reservation
        for(Reservations reservations1 : reservations) {
            if(reservations1.getStartDate().equals(reserve.getStartDate()) &&
                    reservations1.getEndDate().equals(reserve.getEndDate())){
                result.addErrorMessage("Reservation is taken already!");
                return result;
            }
        }

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

        // No past dates
        if (reservations.getEndDate().isEqual(reservations.getStartDate())) {
            result.addErrorMessage("Reservation cannot be the same day!");
        }

        //date can't be in the past
        if(reservations.getStartDate().isBefore(LocalDate.now())){
            result.addErrorMessage("Reservation date cannot be before the current date!");
        }

    }

    public Result<Reservations> deleteById(int reserveId, Host host) throws DataException {
        Result<Reservations> result = new Result<>();
        if(!reservationsRepository.deleteById(reserveId, host)){
            result.addErrorMessage("Reservation ID does not exist!");
        }
        return result;
    }

    //UPDATE
    public Result<Reservations> update(Reservations reservations, Host host) throws DataException {
        Result<Reservations> result = validate(reservations, host);
        if(!result.isSuccess()){
            return result; // we can't go further if the result fails
        }
        boolean updated = reservationsRepository.update(reservations,host);

        if(!updated){
            result.addErrorMessage(String.format("Reservation with id %s does not exist", reservations.getReserveId()));
        }

        return result;
    }

    public Reservations findById(int reservationId, Host host) throws DataException {
        return reservationsRepository.findById(reservationId,host);
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