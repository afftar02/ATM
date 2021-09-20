package com.mycompany.atm.card.information;

import com.mycompany.atm.data.Data;
import com.mycompany.atm.view.View;
import com.mycompany.atm.card.Card;

public class PinCode {
    public int value;

    public PinCode(){

    }

    public boolean isCorrect(Card card){
        for (Card cardFromBD: Data.cards) {
            if(card.number.value.equals(cardFromBD.number.value)){
                card.pinCode.value=cardFromBD.pinCode.value;
            }
        }
        if(card.pinCode.value==this.value){
            return true;
        }
        View.wrongPinCodeNotification();
        return false;
    }
}
