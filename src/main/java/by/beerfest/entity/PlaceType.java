package by.beerfest.entity;

public enum PlaceType {
    SMALL("Маленький"),MEDIUM("Средний"),LARGE("Большой");

    PlaceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    String name;
}
