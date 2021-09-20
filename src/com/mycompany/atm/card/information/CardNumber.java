package com.mycompany.atm.card.information;

import com.mycompany.atm.data.Data;
import com.mycompany.atm.view.View;
import com.mycompany.atm.card.Card;

public class CardNumber {
    public String value;

    public CardNumber(){

    }

    public boolean isCorrectFormat(){
        //format check
        String number = this.value;
        if(number.length()!=19){
            return false;
        }
        else if(number.charAt(4)!='-'||number.charAt(9)!='-'||number.charAt(14)!='-'){
            return false;
        }
        else{
            for(int i=0;i<number.length();i++){
                if(i!=4 && i!=9 && i!=14){
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
        for (Card card: Data.cards) {
            if(this.value.equals(card.number.value)){
                return true;
            }
        }
        View.absentCardNumberNotification();
        return false;
    }
}
