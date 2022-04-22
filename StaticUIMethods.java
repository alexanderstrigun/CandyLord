package de.materna.game;

import java.sql.SQLOutput;
import java.util.Scanner;

public class StaticUIMethods {

    public static int getUserInput() {
        int i = new Scanner(System.in).nextInt();
        return i;
    }


    public static String showPrices() {
        String result = "";
        int count = 1;
        for (CityEnum i : CityEnum.values()) {
            result += "[" + count + "] " + i.toString().toLowerCase() + " " + "[" + i.travelPrice + "]" + "\n";
            count++;
        }
        return result;
    }

    public static void buyOptions(Player player) {
        System.out.println("What candy do you want to buy?");
        System.out.println("[1] Chocolate\n[2] Vine gums\n[3] Skittles\n[4] Acid Drops\n[5] Cotton Candy\n[6] Gummy Bears");
        switch (getUserInput()) {
            case 1 -> player.buyCandy("CHOCOLATE");
            case 2 -> player.buyCandy("VINEGUMS");
            case 3 -> player.buyCandy("SKITTLES");
            case 4 -> player.buyCandy("ACIDDROPS");
            case 5 -> player.buyCandy("COTTONCANDY");
            case 6 -> player.buyCandy("GUMMYBEARS");
            default -> System.out.println("Please choose a candy ya wanna buy...");
        }
    }

    public static void sellOptions(Player player) {
        System.out.println("Which candies do you want to sell?");
        System.out.println("[1] Chocolate\n[2] Vine gums\n[3] Skittles\n [4] Acid Drops\n [5] Cotton Candy\n [6] Gummy Bears");
        switch (getUserInput()) {
            case 1 -> player.sellCandy("CHOCOLATE");
            case 2 -> player.sellCandy("VINEGUMS");
            case 3 -> player.sellCandy("SKITTLES");
            case 4 -> player.sellCandy("ACIDDROPS");
            case 5 -> player.sellCandy("COTTONCANDY");
            case 6 -> player.sellCandy("GUMMYBEARS");
        }
    }

    public static void travelOptions(Player player) {
        System.out.println("Candy cities");
        System.out.println(showPrices());
        System.out.println("Where do ya wanna go?");
        switch (getUserInput()) {
            case 1 -> player.travel("FRANKFURT");
            case 2 -> player.travel("BERLIN");
            case 3 -> player.travel("MUNICH");
            case 4 -> player.travel("COLOGNE");
            case 5 -> player.travel("STUTTGART");
            default -> System.out.println("please choose a proper destination");

        }
    }

    public static void lendOptions(Player player, LoanShark loanShark) {
        System.out.println("-----What do you want from me?-----\n[1] Borrow money or\n[2] Pay money back?");
        switch (getUserInput()) {
            case 1:
                System.out.println("how much do you need?");
                loanShark.lendMoney(player);
        }
    }

    public static void overview(Player player, LoanShark loanShark) {
        System.out.println(
                "---***CANDY LORD***-----\n" +
                        "------------------------\n" +
                        "Your location:  " + player.city.name + "\n" +
                        "Your health:   " + player.health + "\n" +
                        "You hold  " + player.currentHold + "    (" + player.maxHold + "max)" + "\n" +
                        "Your Cash:   " + player.cash + "\n" +
                        "In debt:   " + loanShark.debtClaim + "  (" + loanShark.daysUntilCollect + ")" + "\n" +
                        "------------------------\n" +
                        "Your candy:\n" +
                        "-Chocolate " + player.findPlayerCandy("CHOCOLATE").amount + "\n" +
                        "-Vine gum  " + player.findPlayerCandy("VINEGUM").amount + "\n" +
                        "-Skittles  " + player.findPlayerCandy("SKITTLES").amount + "\n" +
                        "-Acid Drops " + player.findPlayerCandy("ACIDDROPS").amount + "\n" +
                        "-Cotton Candy  " + player.findPlayerCandy("COTTONCANDY").amount + "\n" +
                        "-Gummy Bears  " + player.findPlayerCandy("GUMMYBEARS").amount + "\n" +
                        "------------------------\n" +
                        "Street prices:\n" +
                        "-Chocolate " + player.city.findCityCandy("CHOCOLATE").streetPrice + "\n" +
                        "-Vine gum  " + player.city.findCityCandy("VINEGUM").streetPrice + "\n" +
                        "-Skittles  " + player.city.findCityCandy("SKITTLES").streetPrice + "\n" +
                        "-Acid Drops " + player.city.findCityCandy("ACIDDROPS").streetPrice + "\n" +
                        "-Cotton Candy  " + player.city.findCityCandy("COTTONCANDY").streetPrice + "\n" +
                        "-Gummy Bears  " + player.city.findCityCandy("GUMMYBEARS").streetPrice + "\n"
        );
        System.out.println("-------what do you wanna do?--------");
        System.out.println("[1] Buy candy");
        System.out.println("[2] Sell candy");
        System.out.println("[3] Jet to another city");
        System.out.println("[4] Lend money from loanshark");
    }

    public static void showGameResults(Player player, LoanShark loanShark) {
        String endGameMessage= player.health==0? "You are dead": "5 days are over";
        int netCash=(player.cash - loanShark.debtClaim);
        int increase=(netCash/player.initialCash)*100;
        System.out.println();
        System.out.println(endGameMessage);
        System.out.println("You have " +player.health + " health left\n"+
                "Cash : " + player.cash +"\n"+
                "Debt : " +loanShark.debtClaim+"\n"+
                "-------\n"+
                "Net cash: " +netCash+"\n\n"+
                "PERCENT START CASH: " + increase
                );

    }
}
