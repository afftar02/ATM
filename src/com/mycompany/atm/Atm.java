package com.mycompany.atm;

import com.mycompany.atm.control.BlockingControl;
import com.mycompany.atm.card.BlockedCard;
import com.mycompany.atm.card.Card;
import com.mycompany.atm.data.Data;
import com.mycompany.atm.input.MyScanner;
import com.mycompany.atm.view.View;

import java.util.Date;

public class Atm {
    private static double moneyLimit;

    public static double getMoneyLimit(){
        return moneyLimit;
    }
    private static void setMoneyLimit(double value){
        moneyLimit = value;
    }

    public static void decreaseMoneyLimit(double money){
        moneyLimit -= money;
    }

    public static void increaseMoneyLimit(double money){
        moneyLimit += money;
    }

    public Atm(double money){
        this.moneyLimit = money;
    }

    public static Card start(){
        do {
            if(View.firstMenu()){
                return null;
            }
            Card card = new Card(MyScanner.inputCardNumber());
            if (card.getNumber().isCorrectFormat() && card.getNumber().isExists()) {
                card = Data.dataGetting(card);
                if (card.getClass() != BlockedCard.class) {
                    while (card.getRestOfAttempts() > 0) {
                        if (MyScanner.isInputPinCode(card)) {
                            return card;
                        }
                    }
                    BlockedCard blockedCard = BlockingControl.block(card);
                    return blockedCard;
                } else {
                    if (BlockingControl.isBlockTimeOver((BlockedCard) card)) {
                        card = BlockingControl.unblock((BlockedCard) card);
                        while (card.getRestOfAttempts() > 0) {
                            if(MyScanner.isInputPinCode(card)){
                                return card;
                            }
                        }
                        BlockedCard blockedCard = BlockingControl.block(card);
                        return blockedCard;
                    } else {
                        Date now = new Date();
                        long timeLeft = ((BlockedCard)card).getUnblockingTime().getTime() - now.getTime();
                        View.timeToUnblockNotification(timeLeft);
                    }
                }
            } else if (!card.getNumber().isCorrectFormat()) {
                View.incorrectCardNumberFormatNotification();
            }
        }while(true);
    }
}
