package com.mycompany.atm.card;

import com.mycompany.atm.card.information.Account;
import com.mycompany.atm.card.information.CardNumber;
import com.mycompany.atm.card.information.PinCode;

public class Card {
    public CardNumber number=new CardNumber();
    public Account account=new Account();
    public PinCode pinCode=new PinCode();

    public Card(String cardNumber,String pinCode, double moneyCount){
        number.value = cardNumber;
        this.pinCode.value = Integer.parseInt(pinCode);
        account.moneyCount = moneyCount;
    }

    public Card(CardNumber number){
        this.number.value = number.value;
    }

    public Card(BlockedCard blockedCard){
        number.value = blockedCard.number.value;
        pinCode.value = blockedCard.pinCode.value;
        account.moneyCount = blockedCard.account.moneyCount;
    }

    public Card(){  }
}
