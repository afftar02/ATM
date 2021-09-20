package com.mycompany.atm.card;

import com.mycompany.atm.card.information.Account;
import com.mycompany.atm.card.information.CardNumber;
import com.mycompany.atm.card.information.PinCode;

public class Card {
    protected CardNumber number = new CardNumber();
    protected Account account = new Account();
    protected PinCode pinCode = new PinCode();

    public CardNumber getNumber(){
        return number;
    }
    private void setNumber(CardNumber value){
        number = value;
    }

    public Account getAccount(){
        return account;
    }
    private void setAccount(Account value){
        account = value;
    }

    public PinCode getPinCode(){
        return pinCode;
    }
    private void setPinCode(PinCode value){
        pinCode = value;
    }

    public Card(String cardNumber,String pinCode, double moneyCount){
        this.number = new CardNumber(cardNumber);
        this.pinCode = new PinCode(Integer.parseInt(pinCode));
        this.account = new Account(moneyCount);
    }

    public Card(String cardNumber,int pinCode, double moneyCount){
        this.number = new CardNumber(cardNumber);
        this.pinCode = new PinCode(pinCode);
        this.account = new Account(moneyCount);
    }

    public Card(Card card){
        this.number = card.getNumber();
        this.pinCode = card.getPinCode();
        this.account = card.getAccount();
    }

    public Card(CardNumber number){
        this.number = new CardNumber(number.getValue());
    }

    public Card(BlockedCard blockedCard){
        this.number = new CardNumber(blockedCard.getNumber().getValue());
        this.pinCode = new PinCode(blockedCard.getPinCode().getValue());
        this.account = new Account(blockedCard.getAccount().getMoneyCount());
    }

    public Card(){

    }

    public Card(PinCode pinCode){
        this.pinCode = new PinCode(pinCode.getValue());
    }
}
