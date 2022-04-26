package de.materna.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StaticUIMethods {

    public static int getUserInput() throws InputMismatchException {
        int i;
        try {
            i = new Scanner(System.in).nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Invalid action");
            i = 0;
        }
        return i;
    }


    public static void startGame() {
        int daysLeft = 10;
        Player player = new Player();
        LoanShark loanShark = new LoanShark();
        int transactionOutcome = 0;
        while (true) {
            loanShark.updateDuePayDay();
            loanShark.updatePlayerHealthAndGrantDelay(player);
            Randomevent.createRandomEvent(player, daysLeft);
            if (daysLeft == 0 || player.health == 0) {
                StaticUIMethods.showGameResults(player, loanShark);
                return;
            }
            StaticUIMethods.playerStatsAndMainOptions(player, loanShark, transactionOutcome);
            transactionOutcome = switch (StaticUIMethods.getUserInput()) {
                case 1 -> StaticUIMethods.buyOptions(player);
                case 2 -> StaticUIMethods.sellOptions(player);
                case 3 -> StaticUIMethods.travelOptions(player);
                case 4 -> StaticUIMethods.lendOptions(player, loanShark);
                default -> 0;
            };
            System.out.println(transactionOutcome);
            loanShark.updateDebtClaim();
            daysLeft--;
        }
    }


    public static void playerStatsAndMainOptions(Player player, LoanShark loanShark, int transaction) {
        System.out.println("=".repeat(20) + "***CANDY LORD***" + "=".repeat(20) + "\n" +
                "| Your location: " + player.city.name + " ".repeat((9 - player.city.name.length()) + 7) + "Your health: " + player.health + " ".repeat(String.valueOf(player.health).length() + 3) + "|\n" +
                "| You hold: " + player.currentHold + " ".repeat((String.valueOf(player.maxHold).length() - String.valueOf(player.currentHold).length()) + 2) + "(" + player.maxHold + "max)" + " ".repeat(8) + "Your Cash:   " + player.cash + " ".repeat((4 - String.valueOf(player.cash).length()) + 5) + "|\n" +
                "| In debt:  " + loanShark.debtClaim + " ".repeat((5 - String.valueOf(loanShark.debtClaim).length())) + "(" + loanShark.daysUntilCollect + ")" + " ".repeat((2 - String.valueOf(loanShark.daysUntilCollect).length()) + 34) + "|\n" +
                "-".repeat(56) + "\n" +
                "| +++Your candy+++:" + " ".repeat(10) + "+++Street prices+++:" + " ".repeat(10) + "\n" +
                "| -Chocolate:     " + player.findPlayerCandy("CHOCOLATE").amount + " ".repeat((9 - String.valueOf(player.findPlayerCandy("CHOCOLATE").amount).length())) + "-Chocolate:     " + player.city.findCityCandy("CHOCOLATE").streetPrice + " ".repeat((5 - String.valueOf(player.city.findCityCandy("CHOCOLATE").streetPrice).length()) + 7) + "|\n" +
                "| -Vine gum:      " + player.findPlayerCandy("VINEGUM").amount + " ".repeat((9 - String.valueOf(player.findPlayerCandy("VINEGUM").amount).length())) + "-Vine gum:      " + player.city.findCityCandy("VINEGUM").streetPrice + " ".repeat((5 - String.valueOf(player.city.findCityCandy("VINEGUM").streetPrice).length()) + 7) + "|\n" +
                "| -Skittles:      " + player.findPlayerCandy("SKITTLES").amount + " ".repeat((9 - String.valueOf(player.findPlayerCandy("SKITTLES").amount).length())) + "-Skittles:      " + player.city.findCityCandy("SKITTLES").streetPrice + " ".repeat((5 - String.valueOf(player.city.findCityCandy("SKITTLES").streetPrice).length()) + 7) + "|\n" +
                "| -Acid Drops:    " + player.findPlayerCandy("ACIDDROPS").amount + " ".repeat((9 - String.valueOf(player.findPlayerCandy("ACIDDROPS").amount).length())) + "-Acid Drops:    " + player.city.findCityCandy("ACIDDROPS").streetPrice + " ".repeat((5 - String.valueOf(player.city.findCityCandy("ACIDDROPS").streetPrice).length()) + 7) + "|\n" +
                "| -Cotton Candy:  " + player.findPlayerCandy("COTTONCANDY").amount + " ".repeat((9 - String.valueOf(player.findPlayerCandy("COTTONCANDY").amount).length())) + "-Cotton Candy:  " + player.city.findCityCandy("COTTONCANDY").streetPrice + " ".repeat((5 - String.valueOf(player.city.findCityCandy("COTTONCANDY").streetPrice).length()) + 7) + "|\n" +
                "| -Gummy Bears:   " + player.findPlayerCandy("GUMMYBEARS").amount + " ".repeat((9 - String.valueOf(player.findPlayerCandy("GUMMYBEARS").amount).length())) + "-Gummy Bears:   " + player.city.findCityCandy("GUMMYBEARS").streetPrice + " ".repeat((5 - String.valueOf(player.city.findCityCandy("GUMMYBEARS").streetPrice).length()) + 7) + "|\n" +
                "=".repeat(56) + "\n");
        printTransactionMessage(transaction);
        System.out.println("-------what do you wanna do?--------");
        System.out.println("[1] Buy candy");
        System.out.println("[2] Sell candy");
        System.out.println("[3] Jet to another city");
        System.out.println("[4] Go to loanshark");

    }

    public static void printTransactionMessage(int a) {
        String msg = "";
        switch (a) {
            case 1 -> msg = "You can´t afford this!\n";
            case 2 -> msg = "You can´t carry this amount of candy\n";
            case 3 -> msg = "Successfully bought candy\n";
            case 4 -> msg = "you do not have this amount of candy\n";
            case 5 -> msg = "Successfully sold candy\n";
            case 6 -> msg = "you do not have enough money to travel!\n";
            case 7 -> msg = "You are already in this city\n";
            case 8 -> msg = "You successfully travelled\n";
            case 9 -> msg = "you dont owe me that much\n";
            case 10 -> msg = "you dont have this amount of money\n";
            case 11 -> msg = "Thanks you are now debt-free\n";
            case 12 -> msg = "Thanks, but dont forget that you still owe me money\n";
            case 13 -> msg = "Thats not worth the effort\n";
            case 14 -> msg = "To much of a risk\n";
            case 15 -> msg = "no way, you already owe me too much\n";
            case 16 -> msg = "Alright, I´ll lend you the money\n";

        }
        System.out.println(msg.toUpperCase());
    }


    public static int buyOptions(Player player) {
        System.out.println("What candy do you want to buy?");
        System.out.println("[1] Chocolate\n[2] Vine gums\n[3] Skittles\n[4] Acid Drops\n[5] Cotton Candy\n[6] Gummy Bears");
        int i = switch (getUserInput()) {
            case 1 -> player.buyCandy("CHOCOLATE");
            case 2 -> player.buyCandy("VINEGUM");
            case 3 -> player.buyCandy("SKITTLES");
            case 4 -> player.buyCandy("ACIDDROPS");
            case 5 -> player.buyCandy("COTTONCANDY");
            case 6 -> player.buyCandy("GUMMYBEARS");
            default -> 0;
        };
        return i;
    }

    public static int sellOptions(Player player) {
        System.out.println("Which candies do you want to sell?");
        System.out.println("[1] Chocolate\n[2] Vine gums\n[3] Skittles\n[4] Acid Drops\n[5] Cotton Candy\n[6] Gummy Bears");
        int i = switch (getUserInput()) {
            case 1 -> player.sellCandy("CHOCOLATE");
            case 2 -> player.sellCandy("VINEGUM");
            case 3 -> player.sellCandy("SKITTLES");
            case 4 -> player.sellCandy("ACIDDROPS");
            case 5 -> player.sellCandy("COTTONCANDY");
            case 6 -> player.sellCandy("GUMMYBEARS");
            default -> 0;
        };
        return i;
    }

    public static int travelOptions(Player player) {
        System.out.println("Candy cities");
        System.out.println(showTravelPrices(player));
        System.out.println("Where do ya wanna go?");
        int i = switch (getUserInput()) {
            case 1 -> player.travel("FRANKFURT");
            case 2 -> player.travel("BERLIN");
            case 3 -> player.travel("MUNICH");
            case 4 -> player.travel("COLOGNE");
            case 5 -> player.travel("STUTTGART");
            default -> 0;
        };
        return i;
    }


    public static String showTravelPrices(Player player) {
        String result = "";
        int count = 1;
        String priceInfo;
        for (CityEnum i : CityEnum.values()) {
            priceInfo = i.toString() == player.city.name ? "" : "[" + StaticMethods.calculateTravelPrice(player, i.toString()) + "]";
            result += "[" + count + "] " + i.toString().toLowerCase() + " " + priceInfo + "\n";
            count++;
        }
        return result;
    }

    public static int lendOptions(Player player, LoanShark loanShark) {
        System.out.println("-----What do you want from me?-----\n[1] Borrow money or\n[2] Pay money back?");
        int i = switch (getUserInput()) {
            case 1 -> loanShark.lendMoney(player);
            case 2 -> player.payMoneyBack(loanShark);
            default -> 0;
        };
        return i;
    }


    public static void showGameResults(Player player, LoanShark loanShark) {
        String endGameMessage = player.health == 0 ? "You are dead" : "5 days are over";
        int netCash = (player.cash - loanShark.debtClaim);
        int increase = (netCash / player.initialCash) * 100;
        System.out.println();
        System.out.println(endGameMessage);
        System.out.println("You have " + player.health + " health left\n" +
                "Cash : " + player.cash + "\n" +
                "Debt : " + loanShark.debtClaim + "\n" +
                "-------\n" +
                "Net cash: " + netCash + "\n\n" +
                "PERCENT START CASH: " + increase
        );

    }
}
