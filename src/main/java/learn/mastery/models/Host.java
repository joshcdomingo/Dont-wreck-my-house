package learn.mastery.models;

import java.math.BigDecimal;

public class Host {
    private String hostId;
    private String lastName;
    private String emailAddr;
    private String phoneNum;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private BigDecimal standRate;
    private BigDecimal weekRate;

    public Host() {

    }

    public Host(String hostId, String lastName, String emailAddr, String phoneNum, String address, String city, String state, String postalCode, BigDecimal standRate, BigDecimal weekRate) {
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public BigDecimal getStandRate() {
        return standRate;
    }

    public void setStandRate(BigDecimal standRate) {
        this.standRate = standRate;
    }

    public BigDecimal getWeekRate() {
        return weekRate;
    }

    public void setWeekRate(BigDecimal weekRate) {
        this.weekRate = weekRate;
    }
}
