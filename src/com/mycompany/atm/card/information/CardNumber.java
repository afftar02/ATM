package com.mycompany.atm.card.information;

import com.mycompany.atm.data.Data;
import com.mycompany.atm.view.View;
import com.mycompany.atm.card.Card;

public class CardNumber {
    private String value;

    public String getValue(){
        return value;
    }
    private void setValue(String value){
        this.value = value;
    }

    public CardNumber(String value){
        this.value = value;
    }

    public CardNumber(){

    }

    public boolean isCorrectFormat(){
        //format check
        String number = this.value;
        if(number.length() != 19){
            return false;
        }
        else if(number.charAt(4) != '-' || number.charAt(9) != '-' || number.charAt(14) != '-'){
            return false;
        }
        else{
            for(int i = 0; i < number.length(); i++){
                if(i != 4 && i != 9 && i != 14){
                    char symbol = number.charAt(i);
                    if(!Character.isDigit(symbol)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isExists(){
        for (Card card: Data.getCards()) {
            if(this.getValue().equals(card.getNumber().getValue())){
                return true;
            }
        }
        View.absentCardNumberNotification();
        return false;
    }
}
