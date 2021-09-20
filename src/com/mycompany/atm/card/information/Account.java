package com.mycompany.atm.card.information;

import com.mycompany.atm.Atm;
import com.mycompany.atm.view.View;

public class Account {
    final int ADDING_MONEY_LIMIT = 1000000;

    public double moneyCount;

    public Account(){
        moneyCount = 0.0;
    }

    public void takeMoney(double moneyCount){
        if(moneyCount<=this.moneyCount && moneyCount<= Atm.moneyLimit) {
            View.successfulTakeMoneyNotification(moneyCount);
            this.moneyCount -= moneyCount;
            Atm.moneyLimit -= moneyCount;
        }
        else if(moneyCount>this.moneyCount){
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
            Atm.moneyLimit += moneyCount;
        }
        else{
            View.addMoneyLimitExceededNotification();
        }
    }

    public void checkBalance(){
        View.balanceOutput(this.moneyCount);
    }
}
