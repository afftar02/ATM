package com.mycompany.atm.card;

import com.mycompany.atm.card.Card;
import com.mycompany.atm.card.information.Account;
import com.mycompany.atm.card.information.CardNumber;
import com.mycompany.atm.card.information.PinCode;

import java.util.Date;

public class BlockedCard extends Card {
    private Date unblockingTime = new Date();

    public Date getUnblockingTime(){
        return unblockingTime;
    }
    private void setUnblockingTime(Date value){
        unblockingTime = value;
    }

    public BlockedCard(String cardNumber,String pinCode, double moneyCount, Date time){
        this.number = new CardNumber(cardNumber);
        this.pinCode = new PinCode(Integer.parseInt(pinCode));
        this.account = new Account(moneyCount);
        this.unblockingTime = time;
    }

    public BlockedCard(Date unblockingTime, Card card){
        this.number = new CardNumber(card.getNumber().getValue());
        this.pinCode = new PinCode(card.getPinCode().getValue());
        this.account = new Account(card.getAccount().getMoneyCount());
        this.unblockingTime = unblockingTime;
    }
}
