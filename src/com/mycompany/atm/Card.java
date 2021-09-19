package com.mycompany.atm;

public class Card {
    CardNumber number=new CardNumber();
    Account account=new Account();
    PinCode pinCode=new PinCode();

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
