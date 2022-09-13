package learn.mastery.data;

import learn.mastery.models.Host;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HostFileRepository implements HostRepository {
    private static final String HEADER = "id,last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate";

    private final String filePath;

    public HostFileRepository(@Value("${HostsFilePath:./data/hosts.csv}") String filePath){

        this.filePath = filePath;
    }

    @Override
    public List<Host> findAll() {
        ArrayList<Host> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            reader.readLine(); // read header

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 10) {
                    result.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {
            // don't throw on read
        }
        return result;
    }

    private Host deserialize(String[] fields) {
        Host result = new Host();
        result.setHostId(fields[0]);
        result.setLastName(fields[1]);
        result.setEmailAddr(fields[2]);
        result.setPhoneNum(fields[3]);
        result.setAddress(fields[4]);
        result.setCity(fields[5]);
        result.setState(fields[6]);
        result.setPostalCode(Integer.parseInt(fields[7]));
        result.setStandRate(Double.parseDouble(fields[8]));
        result.setWeekRate(Double.parseDouble(fields[9]));
        return result;
    }
    private String serialize(Host host) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                host.getHostId(),
                host.getLastName(),
                host.getEmailAddr(),
                host.getPhoneNum(),
                host.getAddress(),
                host.getCity(),
                host.getState(),
                host.getPostalCode(),
                host.getStandRate(),
                host.getWeekRate());
    }

    @Override
    public Host findById(String id) {
        return findAll().stream()
                .filter(i -> i.getHostId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    private void writeToFile(List<Host> hosts) throws DataException {
        try (PrintWriter writer = new PrintWriter(filePath)) {

            writer.println(HEADER);

            if (hosts == null) {
                return;
            }

            for (Host host : hosts) {
                writer.println(serialize(host));
            }

        } catch (FileNotFoundException ex) {
            throw new DataException(ex);
        }
    }


}
