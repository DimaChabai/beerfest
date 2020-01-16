package by.chabai.entity;

public class Guest extends User {
    private int defaultTicketNumber;
    private int mediumTicketNumber;
    private int largeTicketNumber;

    public Guest() {
    }

    public int getDefaultTicketNumber() {
        return defaultTicketNumber;
    }

    public void setDefaultTicketNumber(int defaultTicketNumber) {
        this.defaultTicketNumber = defaultTicketNumber;
    }

    public int getMediumTicketNumber() {
        return mediumTicketNumber;
    }

    public void setMediumTicketNumber(int mediumTicketNumber) {
        this.mediumTicketNumber = mediumTicketNumber;
    }

    public int getLargeTicketNumber() {
        return largeTicketNumber;
    }

    public void setLargeTicketNumber(int largeTicketNumber) {
        this.largeTicketNumber = largeTicketNumber;
    }
}
