package com.mycompany.atm;

import com.mycompany.atm.card.BlockedCard;
import com.mycompany.atm.card.Card;
import com.mycompany.atm.data.Data;
import com.mycompany.atm.data.DataProcessor;
import com.mycompany.atm.input.MyScanner;
import com.mycompany.atm.view.View;

public class Main {

    public static void main(String[] args){
        DataProcessor dataBase = new Data();
        if(dataBase.read()) {
            View.successfulDataReadNotification();
            Card card = Atm.start();
            if (card != null) {
                if (card.getClass() != BlockedCard.class && MyScanner.isPinCodeInputContinue) {
                    View.afterAuthorizationMenu(card);
                }
                if (dataBase.save(card)) {
                    View.successfulDataSaveNotification();
                } else {
                    View.unsuccessfulDataSaveNotification();
                }
            }
        }
        else{
            View.unsuccessfulDataReadNotification();
        }
    }
}