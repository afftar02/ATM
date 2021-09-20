package com.mycompany.atm.card.information;

import com.mycompany.atm.data.Data;
import com.mycompany.atm.view.View;
import com.mycompany.atm.card.Card;

public class PinCode {
    private int value;

    public int getValue(){
        return value;
    }
    private void setValue(int value){
        this.value = value;
    }

    public PinCode(){

    }

    public PinCode(int value){
        this.value = value;
    }

    public boolean isCorrect(Card card){
        for (Card cardFromBD: Data.getCards()) {
            if(card.getNumber().getValue().equals(cardFromBD.getNumber().getValue())){
                card = new Card(cardFromBD);
            }
        }
        if(card.getPinCode().getValue() == this.getValue()){
            return true;
        }
        View.wrongPinCodeNotification();
        return false;
    }
}
