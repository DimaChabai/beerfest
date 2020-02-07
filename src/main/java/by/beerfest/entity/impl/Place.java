package by.beerfest.entity.impl;

import by.beerfest.entity.Entity;
import by.beerfest.entity.PlaceType;

public class Place extends Entity {

    private long idPlace;
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

    public long getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(long idPlace) {
        this.idPlace = idPlace;
    }

    @Override
    public String toString() {
        return "Территория №" + idPlace + " Места = " + seats +
                ", тип = " + type.toString();
    }
}
