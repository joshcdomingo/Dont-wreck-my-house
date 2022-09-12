package learn.mastery.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationsFileRepository implements ReservationsRepository {

    private static final String HEADER = "id,start_date,end_date,guest_id,total";
    private final String directory;

    public ReservationsFileRepository(@Value("${ForageFilePath:./data/reservations}")String directory) {
        this.directory = directory;
    }
}
