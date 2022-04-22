package de.materna.game;

public class LoanShark {

    int maxBorrow = 9999;
    int minBorrow = 1000;
    int debtClaim;
    int daysUntilCollect;
    int grantedDays=2;
    int damageToNonPayer=50;
    double interestRate = 0.05;


    //LoanShark methods
    public void lendMoney(Player player) {
        int amount = StaticUIMethods.getUserInput();
        if (amount < this.minBorrow) {
            System.out.println("thats not worth the effort");
        } else if (amount > this.maxBorrow) {
            System.out.println("to much of a risk, man");
        } else if(amount+this.debtClaim>=this.maxBorrow) {
            System.out.println("no way, you already owe me " +debtClaim);
        } else {
            updateDuePayDay(amount);
            this.debtClaim += amount;
            player.cash += amount;
            System.out.println("you owe me " + this.debtClaim + ".\n Payback in " + this.daysUntilCollect + " days.");
        }
    }


    public void updateDebtClaim() {
        this.debtClaim += (int) ((this.debtClaim * this.interestRate));
    }

    public void updateDuePayDay(int moneyToBorrow) {
        int total = debtClaim + moneyToBorrow;
        if (total >= 1000 && total <= 2000) {
            this.daysUntilCollect = (int) ((-0.004 * total) + 19);
        } else if (total > 1000 && total <= 3500) {
            this.daysUntilCollect = (int) ((-0.002 * total) + 15);
        } else if (total > 3500 && total <= 7000) {
            this.daysUntilCollect = (int) ((-0.0011 * total) + 11.865);
        } else if (total > 7000) {
            this.daysUntilCollect = 3;
        }
    }


    public void updateDuePayDay() {
        daysUntilCollect = debtClaim > 0 && daysUntilCollect > 0 ? daysUntilCollect - 1 : daysUntilCollect;
    }

    public void updatePlayerHealthAndGiveDelay(Player player) {
        player.health = debtClaim > 0 && daysUntilCollect == 0 ? player.health - damageToNonPayer : player.health;
        this.daysUntilCollect = debtClaim > 0 && daysUntilCollect == 0 ? this.daysUntilCollect + grantedDays : this.daysUntilCollect;
    }
}
