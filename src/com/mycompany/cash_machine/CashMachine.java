package com.mycompany.cash_machine;

import java.util.Date;

public class CashMachine {
    static double moneyLimit;

    private static Card tryToInputPinCode(Card card){
        int pinCodeInput = 0;
        do {
            PinCode pinCode = PinCode.input();
            if (pinCode!=null && pinCode.isCorrect(card)) {
                View.successfulAuthorizationNotification();
                return card;
            }
            pinCodeInput++;
        } while (pinCodeInput < 3);
        BlockedCard blockedCard = BlockingControl.block(card);
        return blockedCard;
    }

    public static Card cardNumberAndPinCodeInput(){
        do {
            if(View.firstMenu()){
                return null;
            }
            Card card = new Card(CardNumber.input());
            if (card.number.isCorrectFormat() && card.number.isExists()) {
                card = Data.dataGetting(card);
                if (card.getClass()!=BlockedCard.class) {
                    card = tryToInputPinCode(card);
                    return card;
                } else {
                    if (BlockingControl.isBlockTimeOver((BlockedCard) card)) {
                        card = BlockingControl.unblock((BlockedCard) card);
                        card = tryToInputPinCode(card);
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
