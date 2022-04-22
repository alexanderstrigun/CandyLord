package de.materna.game;

public class Candy {
    String name;
    int amount;
    int streetPrice;

    //Candy constructor
    Candy(String candyName) {
        this.name = candyName;
        this.streetPrice = switch (candyName) {
            case "CHOCOLATE" -> 15000 + (int) (Math.random() * ((30000 - 15000) + 1));
            case "VINEGUM" -> 5000 + (int) (Math.random() * ((14000 - 5000) + 1));
            case "SKITTLES" -> 1000 + (int) (Math.random() * ((4500 - 1000) + 1));
            case "ACIDDROPS" -> 300 + (int) (Math.random() * ((900 - 300) + 1));
            case "COTTONCANDY" -> 70 + (int) (Math.random() * ((250 - 70) + 1));
            case "GUMMYBEARS" -> 10 + (int) (Math.random() * ((60 - 10) + 1));
            default -> 0;
        };
    }

}

