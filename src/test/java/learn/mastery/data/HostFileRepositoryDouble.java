package learn.mastery.data;

import learn.mastery.models.Guest;
import learn.mastery.models.Host;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HostFileRepositoryDouble implements HostRepository{

    public final static Host HOST = new Host("f4d6c5e4-d207-4ce0-93b3-f1d1b397883c","MacTimpany","cmactimpany1u@ebay.com","(203) 7237610","34059 Randy Parkway","Fairfield","CT","06825", BigDecimal.valueOf(476),BigDecimal.valueOf(595));
    private final ArrayList<Host> hosts = new ArrayList<>();

    public HostFileRepositoryDouble() {
        hosts.add(HOST);
    }

    @Override
    public List<Host> findAll() {
        return hosts;
    }

    @Override
    public Host findById(String id) {
        return hosts.stream()
                .filter(i -> i.getHostId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
