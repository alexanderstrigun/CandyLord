package de.materna.game;

public class LoanShark {

    private int maxBorrow = 9999;
    private int minBorrow = 1000;
    public int debtClaim;
    public int daysUntilCollect;
    private int grantedDays = 2;
    private int damageToNonPayer = 50;
    double interestRate = 0.05;


    //LoanShark methods
    public int lendMoney(Player player) {
        System.out.println("how much do you need?");
        int amount = StaticUIMethods.getUserInput();
        int i = 0;
        i = amount < this.minBorrow ? 13 : amount > this.maxBorrow ? 14 : amount + this.debtClaim >= this.maxBorrow ? 15 : 16;
        if (i == 16) {
            updateDuePayDay(amount);
            this.debtClaim += amount;
            player.cash += amount;
        }
        return i;
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
        daysUntilCollect = debtClaim > 0 && daysUntilCollect > 0 ? daysUntilCollect - 1 :
                debtClaim == 0 ? 0 : 0;
    }

    public void updatePlayerHealthAndGrantDelay(Player player) {
        player.health = debtClaim > 0 && daysUntilCollect == 0 ? player.health - damageToNonPayer : player.health;
        this.daysUntilCollect = debtClaim > 0 && daysUntilCollect == 0 ? this.daysUntilCollect + grantedDays : this.daysUntilCollect;
    }
}
