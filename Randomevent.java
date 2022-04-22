package de.materna.game;

public class Randomevent {

    public static void createRandomEvent(Player player, int gameDay) {

        String robbedMessage="";
        int i = 1 + (int) (Math.random() * ((20 - 1) + 1));
        player.cash = gameDay == i ? player.cash * 0 : player.cash;
        robbedMessage=gameDay == i ? "someone robbed all your money" : "";
        System.out.println(robbedMessage);
    }


}
