package de.materna.game;

import java.util.ArrayList;

public class City {

    String name;
    ArrayList<Candy> cityCandy = new ArrayList<>();

    //Constructor method
    City(String cityName) {
        {
            for (CandyEnum test : CandyEnum.values()) {
                cityCandy.add(new Candy(test.toString()));
            }
        }
        this.name = cityName;
    }

    //City method
    public Candy findCityCandy(String candy) {
        Candy foundCityCandy = null;
        for (Candy i : cityCandy) {
            if (i.name == candy) {
                foundCityCandy = i;
            }
        }
        return foundCityCandy;
    }
}


