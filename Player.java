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

        for (CandyEnum test : CandyEnum.values()) {
            playerCandy.add(new Candy(test.toString()));
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
            foundCandy = i.name == candy ? i : foundCandy;
        }
        return foundCandy;
    }

    public int buyCandy(String candy) {
        Candy foundCandy = findPlayerCandy(candy);
        Candy foundCityCandy = city.findCityCandy(candy);
        System.out.println("how much?");
        int amount = StaticUIMethods.getUserInput();
        int i = 0;
        i = (amount * foundCityCandy.streetPrice >= this.cash) ? 1 : (amount > this.maxHold || amount + this.currentHold > this.maxHold) ? 2 : 3;
        foundCandy.amount = i == 3 ? foundCandy.amount + amount : foundCandy.amount;
        this.cash = i == 3 ? this.cash - (amount * foundCityCandy.streetPrice) : this.cash;
        this.currentHold = i == 3 ? this.currentHold + amount : this.currentHold;
        return i;
    }


    public int sellCandy(String candy) {
        System.out.println("how much do you want to sell?");
        Candy foundCandy = findPlayerCandy(candy);
        Candy foundCityCandy = city.findCityCandy(candy);
        int amount = StaticUIMethods.getUserInput();
        int i = foundCandy.amount < amount ? 4 : 5;
        if (i == 5) {
            foundCandy.amount -= amount;
            this.cash += foundCityCandy.streetPrice * amount;
            this.currentHold -= amount;
        }
        return i;
    }


    public int travel(String city) {
        int travelPrice = StaticMethods.calculateTravelPrice(this, city);
        boolean travelImpossible = travelPrice > this.cash || this.city.name == city;
        int i = 0;
        i = travelPrice > this.cash ? 6 : this.city.name == city ? 7 : !travelImpossible ? 8 : i;
        this.city = i == 8 ? new City(city) : this.city;
        this.cash = i == 8 ? this.cash - travelPrice : this.cash;
        return i;
    }

    public int payMoneyBack(LoanShark loanshark) {
        System.out.println("how much do you want to pay back?");
        int amount = StaticUIMethods.getUserInput();
        Boolean payBackPossible = amount <= this.cash && amount <= loanshark.debtClaim ? true : false;
        int i = 0;
        i = amount > loanshark.debtClaim ? 9 : amount > this.cash ? 10 : amount == loanshark.debtClaim ? 11 : amount < loanshark.debtClaim ? 12 : 0;
        loanshark.debtClaim = payBackPossible ? loanshark.debtClaim - amount : loanshark.debtClaim;
        this.cash = payBackPossible ? this.cash - amount : this.cash;
        return i;
    }

}



