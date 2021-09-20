package com.mycompany.atm;

import com.mycompany.atm.blocking.BlockingControl;
import com.mycompany.atm.card.BlockedCard;
import com.mycompany.atm.card.Card;
import com.mycompany.atm.data.Data;
import com.mycompany.atm.input.MyScanner;
import com.mycompany.atm.view.View;

import java.util.Date;

public class Atm {
    public static double moneyLimit;

    public static Card start(){
        do {
            if(View.firstMenu()){
                return null;
            }
            Card card = new Card(MyScanner.inputCardNumber());
            if (card.number.isCorrectFormat() && card.number.isExists()) {
                card = Data.dataGetting(card);
                if (card.getClass()!= BlockedCard.class) {
                    card = MyScanner.tryToInputPinCode(card);
                    return card;
                } else {
                    if (BlockingControl.isBlockTimeOver((BlockedCard) card)) {
                        card = BlockingControl.unblock((BlockedCard) card);
                        card = MyScanner.tryToInputPinCode(card);
                        return card;
                    } else {
                        Date now = new Date();
                        long timeLeft = ((BlockedCard)card).unblockingTime.getTime()-now.getTime();
                        View.timeToUnblockNotification(timeLeft);
                    }
                }
            } else if (!card.number.isCorrectFormat()) {
                View.incorrectCardNumberFormatNotification();
            }
        }while(true);
    }
}
