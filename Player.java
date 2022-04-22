package de.materna.game;

import java.util.ArrayList;

public class Player {
    int cash;
    int initialCash;
    int health;
    int currentHold;
    int maxHold = 100;
    City city;
    ArrayList<Candy> playerCandy = new ArrayList<>();

    //Player constructor
    Player() {
        {
            for (CandyEnum test : CandyEnum.values()) {
                playerCandy.add(new Candy(test.toString()));
            }
        }
        this.cash = 2000;
        this.initialCash = cash;
        this.health = 100;
        city = new City("FRANKFURT");
    }

    //Player methods
    public Candy findPlayerCandy(String candy) {
        Candy foundCandy = null;
        for (Candy i : playerCandy) {
            if (i.name == candy) {
                foundCandy = i;
            }
        }
        return foundCandy;
    }

    public void buyCandy(String candy) {
        Candy foundCandy = findPlayerCandy(candy);
        Candy foundCityCandy = city.findCityCandy(candy);
        System.out.println("how much?");
        int amount = StaticUIMethods.getUserInput();
        if (amount * foundCityCandy.streetPrice >= this.cash) {
            System.out.println("You can´t afford this!");
        } else if (amount > this.maxHold || amount + this.currentHold > this.maxHold) {
            System.out.println("you can´t carry this amount");
        } else {
            foundCandy.amount += amount;
            this.cash -= amount * foundCityCandy.streetPrice;
            this.currentHold += amount;
        }
    }



    public void sellCandy(String candy) {
        Candy foundCandy = findPlayerCandy(candy);
        Candy foundCityCandy = city.findCityCandy(candy);
        System.out.println("how much");
        int amount = StaticUIMethods.getUserInput();
        if (foundCandy.amount < amount) {
            System.out.println("you do not have this amount of candy");
        } else {
            foundCandy.amount -= amount;
            this.cash += foundCityCandy.streetPrice * amount;
            this.currentHold -= amount;
        }
    }


    public void travel(String city) {
        int travelPrice;
        {
            int lookedUpPrice = CityEnum.valueOf(city).travelPrice;
            travelPrice = lookedUpPrice;
        }
        if (travelPrice > this.cash) {
            System.out.println("you do not have enough money to travel!");
        } else if (this.city.name == city) {
            System.out.println("You are already in this city..");
        } else {
            this.city = new City(city);
            this.cash -= travelPrice;
        }
    }
}
