package de.materna.game;

import java.sql.SQLOutput;

public enum CityEnum {
    FRANKFURT (20,50.110924, 8.682127), BERLIN(60,52.520008,13.404954), MUNICH(30, 48.137154, 11.576124), COLOGNE(30, 50.935173, 6.953101), STUTTGART(40, 48.783333, 9.183333);

    public final int travelPrice;
    public final double latitude;
    public final double longitude;


    CityEnum(int travelPrice, double latitude, double longitude) {
        this.travelPrice = travelPrice;
        this.latitude=latitude;
        this.longitude=longitude;
    }


}
