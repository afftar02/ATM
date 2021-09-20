package com.mycompany.atm.card;

import com.mycompany.atm.card.Card;

import java.util.Date;

public class BlockedCard extends Card {
    public Date unblockingTime = new Date();

    public BlockedCard(String cardNumber,String pinCode, double moneyCount, Date time){
        number.value = cardNumber;
        this.pinCode.value = Integer.parseInt(pinCode);
        account.moneyCount = moneyCount;
        this.unblockingTime=time;
    }

    public BlockedCard(Card card){
        number.value = card.number.value;
        this.pinCode.value = card.pinCode.value;
        account.moneyCount = card.account.moneyCount;
    }
}