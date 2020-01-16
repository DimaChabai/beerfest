package by.chabai.entity;

public class Place {

    private long id_place;
    private int seats;
    private PlaceType type;

    public Place() {
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public PlaceType getType() {
        return type;
    }

    public void setType(PlaceType type) {
        this.type = type;
    }

    public long getId_place() {
        return id_place;
    }

    public void setId_place(long id_place) {
        this.id_place = id_place;
    }

    @Override
    public String toString() {
        return "Территория №" + id_place + " Места = " + seats +
                ", размер = " + type.getName();
    }
}
