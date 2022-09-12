package learn.mastery.data;

import learn.mastery.models.Reservations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationsFileRepository implements ReservationsRepository {

    private static final String HEADER = "id,start_date,end_date,guest_id,total";
    private final String directory;

    public ReservationsFileRepository(@Value("${ForageFilePath:./data/reservations}")String directory) {
        this.directory = directory;
    }

    @Override
    public List<Reservations> findByReservations(String reservations) {
        ArrayList<Reservations> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(reservations)))) {

            reader.readLine(); // read header

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 4) {
                    result.add(deserialize(fields, reservations));
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

    private Reservations deserialize(String[] fields, String reservations) {
        Reservations result = new Reservations();
        return  result;
    }


}
