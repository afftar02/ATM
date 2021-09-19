package com.mycompany.cash_machine;

public class Account {
    double moneyCount;

    public void takeMoney(double moneyCount){
        if(moneyCount<=this.moneyCount && moneyCount<=CashMachine.moneyLimit) {
            View.successfulTakeMoneyNotification(moneyCount);
            this.moneyCount -= moneyCount;
            CashMachine.moneyLimit -= moneyCount;
        }
        else if(moneyCount>this.moneyCount){
            View.notEnoughAccountMoneyNotification(moneyCount);
        }
        else{
            View.atmMoneyLimitExceededNotification();
        }
    }

    public void addMoney(double moneyCount){
        if(moneyCount<=1000000) {
            View.successfulAddMoneyNotification(moneyCount);
            this.moneyCount += moneyCount;
            CashMachine.moneyLimit += moneyCount;
        }
        else{
            View.addMoneyLimitExceededNotification();
        }
    }

    public void checkBalance(){
        View.balanceOutput(this.moneyCount);
    }
}
