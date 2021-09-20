package com.mycompany.atm.card.information;

import com.mycompany.atm.Atm;
import com.mycompany.atm.view.View;

public class Account {
    final int ADDING_MONEY_LIMIT = 1000000;

    private double moneyCount;

    public double getMoneyCount(){
        return moneyCount;
    }
    private void setMoneyCount(double value){
        moneyCount = value;
    }

    public Account(){
        this.moneyCount = 0.0;
    }

    public Account(double money){
        this.moneyCount = money;
    }

    public void takeMoney(double moneyCount){
        if(moneyCount<=this.getMoneyCount() && moneyCount<= Atm.getMoneyLimit()) {
            View.successfulTakeMoneyNotification(moneyCount);
            this.moneyCount -= moneyCount;
            Atm.decreaseMoneyLimit(moneyCount);
        }
        else if(moneyCount>this.getMoneyCount()){
            View.notEnoughAccountMoneyNotification(moneyCount);
        }
        else{
            View.atmMoneyLimitExceededNotification();
        }
    }

    public void addMoney(double moneyCount){
        if(moneyCount <= ADDING_MONEY_LIMIT) {
            View.successfulAddMoneyNotification(moneyCount);
            this.moneyCount += moneyCount;
            Atm.increaseMoneyLimit(moneyCount);
        }
        else{
            View.addMoneyLimitExceededNotification();
        }
    }

    public void checkBalance(){
        View.balanceOutput(this.getMoneyCount());
    }
}
