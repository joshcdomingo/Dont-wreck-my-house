package learn.mastery.data;

import learn.mastery.models.Guest;
import learn.mastery.models.Host;
import learn.mastery.models.Reservations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationsFileRepository implements ReservationsRepository {

    private static final String HEADER = "id,start_date,end_date,guest_id,total";
    private final String directory;

    public ReservationsFileRepository(@Value("${ReservationsFilePath:./data/reservations}")String directory) {
        this.directory = directory;
    }

//    @Override
//    public Reservations add(Reservations reservations) throws DataException {
//        List<Reservations> all = findByReservations(reservations.getHost().getHostId());
//        reservations.getReserveId();
//        all.add(reservations);
//        writeAll(all, reservations.getHost().getHostId());
//        return reservations;
//    }


    //CREATE
    @Override
    public Reservations add(Reservations reservations) throws DataException {
        List<Reservations> all = findByReservations(reservations.getHost().getHostId());
        int nextId = all.stream()
                .mapToInt(Reservations::getReserveId)
                .max()
                .orElse(0) + 1;

        reservations.setReserveId(nextId);

        all.add(reservations);
        writeAll(all, reservations.getHost().getHostId());
        return reservations;
    }

    //UPDATE
    @Override
    public boolean update(Reservations reservations, String hostsId) throws DataException {
        List<Reservations> all = findByReservations(hostsId);
        // loop through all the entries
        for(int i = 0; i < all.size(); i++){
            // if the current index id matches the logEntry id
            if(all.get(i).getReserveId() == reservations.getReserveId()){
                // update that index with the log entry provided
                all.set(i, reservations);
                // then I want to re-write that entire array to the file
                writeAll(all, hostsId);
                // I want to return true so we know this was successful
                return true;
            }
        }
        // if I've looped through everything and was not able to update anything I am going to return false because this update was not successful
        return false;
    }

    //DELETE
    @Override
    public boolean deleteById(int reserveId, String hostsId) throws  DataException {
        List<Reservations> all = findByReservations(hostsId);
        for(int i = 0; i < all.size(); i++){
            if(all.get(i).getReserveId() == reserveId){
                all.remove(i);
                writeAll(all,hostsId);
                return true;
            }
        }
        return false;
    }


    @Override
    public List<Reservations> findByReservationsByEmail(Host reservations) {
        return null;
    }

    @Override
    public List<Reservations> findByReservationsByEmail(String email) {
        return null;
    }


    private void writeAll(List<Reservations> reserve, String hostsId) throws DataException {
        try (PrintWriter writer = new PrintWriter(getFilePath(hostsId))) {

            writer.println(HEADER);

            for (Reservations reserves : reserve) {
                writer.println(serialize(reserves));
            }
        } catch (FileNotFoundException ex) {
            throw new DataException(ex);
        }
    }

    @Override
    public List<Reservations> findByReservations(String hostsId) {
        ArrayList<Reservations> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(hostsId)))) {

            reader.readLine(); // read header

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 5) {
                    result.add(deserialize(fields, hostsId));
                }
            }
        } catch (IOException ex) {
            // don't throw on read
        }
        return result;
    }


    private String getFilePath(String reservations) {
        return Paths.get(directory, reservations + ".csv").toString();
    }
    private String serialize(Reservations reservations) {
        return String.format("%s,%s,%s,%s,%s",
                reservations.getReserveId(),
                reservations.getStartDate(),
                reservations.getEndDate(),
                reservations.getGuest().getGuestId(),
                reservations.getTotal());
    }
    private Reservations deserialize(String[] fields, String hostsId) {
        Reservations result = new Reservations();

        Host host = new Host();
        host.setHostId(hostsId);
        result.setHost(host);


        result.getHost().setHostId(hostsId);

        result.setReserveId(Integer.parseInt(fields[0]));
        result.setStartDate(LocalDate.parse(fields[1]));
        result.setEndDate(LocalDate.parse(fields[2]));
        result.setTotal(BigDecimal.valueOf(Double.parseDouble(fields[4])));

        Guest guest = new Guest();
        guest.setGuestId(Integer.parseInt(fields[3]));
        result.setGuest(guest);

        return  result;
    }

    @Override
    public Reservations findById(int reservationsId,String hostsId)  {
        List<Reservations> all = findByReservations(hostsId);
        for(Reservations reservations : all){
            if(reservations.getReserveId() == reservationsId){
                return  reservations;
            }
        }
        return null;
    }

}
