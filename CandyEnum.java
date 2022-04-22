package de.materna.game;

public enum CandyEnum {
    CHOCOLATE(50), VINEGUM(40), SKITTLES(30), ACIDDROPS(20), COTTONCANDY(10), GUMMYBEARS(5);

    public final int priceFactor;

    CandyEnum(int priceFactor) {
        this.priceFactor = priceFactor;
    }
}
