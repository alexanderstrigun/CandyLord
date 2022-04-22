package de.materna.game;

import java.sql.SQLOutput;

public enum CityEnum {
    FRANKFURT(50), BERLIN(60), MUNICH(30), COLOGNE(30), STUTTGART(40);

    public final int travelPrice;

    CityEnum(int travelPrice) {
        this.travelPrice = travelPrice;
    }


}
