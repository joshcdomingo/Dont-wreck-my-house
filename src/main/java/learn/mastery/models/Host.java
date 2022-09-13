package learn.mastery.models;

public class Host {
    private String hostId;
    private String lastName;
    private String emailAddr;
    private String phoneNum;
    private String address;
    private String city;
    private String state;
    private int postalCode;
    private double standRate;
    private double weekRate;

    public Host() {

    }

    public Host(String hostId, String lastName, String emailAddr, String phoneNum, String address, String city, String state, int postalCode, double standRate, double weekRate) {
        this.hostId = hostId;
        this.lastName = lastName;
        this.emailAddr = emailAddr;
        this.phoneNum = phoneNum;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.standRate = standRate;
        this.weekRate = weekRate;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public double getStandRate() {
        return standRate;
    }

    public void setStandRate(double standRate) {
        this.standRate = standRate;
    }

    public double getWeekRate() {
        return weekRate;
    }

    public void setWeekRate(double weekRate) {
        this.weekRate = weekRate;
    }
}
