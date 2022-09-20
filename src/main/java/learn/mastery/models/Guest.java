package learn.mastery.models;
public class Guest {
    private int guestId;
    private String firstName;
    private String lastName;
    private String emailAddr;
    private String phoneNum;
    private String state;

    public Guest() {
    }

    public Guest(int guestId, String firstName, String lastName, String emailAddr, String phoneNum, String state) {
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddr = emailAddr;
        this.phoneNum = phoneNum;
        this.state = state;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
