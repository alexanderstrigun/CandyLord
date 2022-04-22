package de.materna.game;

public class GameController {

    public static void main(String[] args) {
        int daysLeft = 10;
        Player player = new Player();
        LoanShark loanShark = new LoanShark();


        while (true) {
            loanShark.updateDuePayDay();
            loanShark.updatePlayerHealthAndGiveDelay(player);
            Randomevent.createRandomEvent(player, daysLeft);
            if (daysLeft == 0 || player.health==0) {
                StaticUIMethods.showGameResults(player, loanShark);
                return;
            }


            StaticUIMethods.overview(player, loanShark);
            switch (StaticUIMethods.getUserInput()) {
                case 1 -> StaticUIMethods.buyOptions(player);
                case 2 -> StaticUIMethods.sellOptions(player);
                case 3 -> StaticUIMethods.travelOptions(player);
                case 4 -> StaticUIMethods.lendOptions(player, loanShark);
                default -> System.out.println("Please choose an action");
            }
            loanShark.updateDebtClaim();

            daysLeft--;

        }

    }
}

