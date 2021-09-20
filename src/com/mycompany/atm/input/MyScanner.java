package com.mycompany.atm.input;

import com.mycompany.atm.view.View;
import com.mycompany.atm.blocking.BlockingControl;
import com.mycompany.atm.card.BlockedCard;
import com.mycompany.atm.card.Card;
import com.mycompany.atm.card.information.CardNumber;
import com.mycompany.atm.card.information.PinCode;

import java.util.Scanner;

public class MyScanner {
    public static CardNumber inputCardNumber(){
        do {
            try {
                Scanner in = new Scanner(System.in);
                View.inputNotification("номер");
                String number = in.nextLine();
                CardNumber cardNumber = new CardNumber(number);
                return cardNumber;
            } catch (Exception e) {
                View.incorrectInputNotification("Номер");
            }
        }while(true);
    }

    public static Card tryToInputPinCode(Card card){
        try {
            int pinCodeInput = 0;
            do {
                PinCode pinCode = inputPinCode();
                if (pinCode != null && pinCode.isCorrect(card)) {
                    View.successfulAuthorizationNotification();
                    return card;
                }
                pinCodeInput++;
            } while (pinCodeInput < 3);
            BlockedCard blockedCard = BlockingControl.block(card);
            return blockedCard;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static PinCode inputPinCode(){
        try {
            Scanner in = new Scanner(System.in);
            View.inputNotification("пин-код");
            PinCode pinCode = new PinCode();
            pinCode = new PinCode(in.nextInt());
            return pinCode;
        }
        catch (Exception e){
            View.incorrectInputNotification("Пин-код");
            return null;
        }
    }
}
