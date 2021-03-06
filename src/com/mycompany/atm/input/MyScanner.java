package com.mycompany.atm.input;

import com.mycompany.atm.view.View;
import com.mycompany.atm.card.Card;
import com.mycompany.atm.card.information.CardNumber;
import com.mycompany.atm.card.information.PinCode;

import java.util.Scanner;

public class MyScanner {
    public static boolean isPinCodeInputContinue = true;

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

    public static boolean isInputPinCode(Card card){
        try {
            PinCode pinCode = inputPinCode();
            if (pinCode != null && pinCode.isCorrect(card)) {
                View.successfulAuthorizationNotification();
                card.setBaseRestOfAttempts();
                return true;
            }
            else {
                card.decreaseRestOfAttempts();
                if(card.getRestOfAttempts()>0) {
                    if (View.pinCodeInputMenu()) {
                        isPinCodeInputContinue = false;
                        return true;
                    } else {
                        isPinCodeInputContinue = true;
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            if (View.pinCodeInputMenu()) {
                isPinCodeInputContinue = false;
                return true;
            }
            else{
                isPinCodeInputContinue = true;
                return false;
            }
        }
    }

    public static PinCode inputPinCode(){
        try {
            Scanner in = new Scanner(System.in);
            View.inputNotification("пин-код");
            PinCode pinCode = new PinCode(in.nextInt());
            return pinCode;
        }
        catch (Exception e){
            View.incorrectInputNotification("Пин-код");
            return null;
        }
    }
}
