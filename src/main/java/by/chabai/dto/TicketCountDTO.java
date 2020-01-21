package by.chabai.dto;
//@TODO имя
public class TicketCountDTO {

    int defaultTicketCount;
    int mediumTicketCount;
    int largeTicketCount;

    public TicketCountDTO() {
    }

    public TicketCountDTO(int defaultTicketCount, int mediumTicketCount, int largeTicketCount) {
        this.defaultTicketCount = defaultTicketCount;
        this.mediumTicketCount = mediumTicketCount;
        this.largeTicketCount = largeTicketCount;
    }

    public int getDefaultTicketCount() {
        return defaultTicketCount;
    }

    public void setDefaultTicketCount(int defaultTicketCount) {
        this.defaultTicketCount = defaultTicketCount;
    }

    public int getMediumTicketCount() {
        return mediumTicketCount;
    }

    public void setMediumTicketCount(int mediumTicketCount) {
        this.mediumTicketCount = mediumTicketCount;
    }

    public int getLargeTicketCount() {
        return largeTicketCount;
    }

    public void setLargeTicketCount(int largeTicketCount) {
        this.largeTicketCount = largeTicketCount;
    }
}
