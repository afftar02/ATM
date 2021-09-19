package com.mycompany.atm;

import java.util.Scanner;

public class PinCode {
    int value;

    public static PinCode input(){
        try {
            Scanner in = new Scanner(System.in);
            View.inputNotification("пин-код");
            PinCode pinCode = new PinCode();
            pinCode.value = in.nextInt();
            return pinCode;
        }
        catch (Exception e){
            View.incorrectInputNotification("Пин-код");
            return null;
        }
    }

    public boolean isCorrect(Card card){
        for (Card cardFromBD:Data.cards) {
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
