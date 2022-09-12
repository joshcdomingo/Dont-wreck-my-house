package learn.mastery.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class HostFileRepository implements HostRepository {
    private static final String HEADER = "id,last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate";

    private final String filePath;

    public HostFileRepository(@Value("${ForagerFilePath:./data/hosts.csv}") String filePath){

        this.filePath = filePath;
    }
}
