package com.mycompany.cash_machine;

public class Main {

    public static void main(String[] args){
        Data dataBase = new Data();
        dataBase.read();
        Card card = CashMachine.cardNumberAndPinCodeInput();
        if(card!=null) {
            if(card.getClass()!=BlockedCard.class) {
                View.afterAuthorizationMenu(card);
            }
            dataBase.save(card);
        }
    }
}