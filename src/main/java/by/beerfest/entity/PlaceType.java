package by.beerfest.entity;

public enum PlaceType {
    SMALL("Маленький"), MEDIUM("Средний"), LARGE("Большой");

    String name;

    PlaceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
