package com.mycompany.atm.data;

import com.mycompany.atm.Atm;
import com.mycompany.atm.card.BlockedCard;
import com.mycompany.atm.card.Card;
import com.mycompany.atm.card.information.Account;
import com.mycompany.atm.card.information.CardNumber;
import com.mycompany.atm.card.information.PinCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Data implements DataProcessor {
    final String PATH = "Data.txt";

    private static ArrayList<Card> cards= new ArrayList<Card>();

    public static ArrayList<Card> getCards(){
        return cards;
    }
    private static void setCards(ArrayList<Card> value){
        cards = value;
    }

    public boolean read(){
        try {
            FileReader fileReader = new FileReader(PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            Atm atm = new Atm(Double.parseDouble(line));
            while(line!=null){
                line = bufferedReader.readLine();
                if (line!=null) {
                    String[] words = line.split(" ");
                    if(words.length>3) {
                        String time = words[3] + ", " + words[4] + ' ' + words[5] + ' ' + words[8] + ' ' + words[6];
                        DateFormat dateFormat = new SimpleDateFormat("E, MMMM dd yyyy HH:mm:ss", Locale.ENGLISH);
                        Date date = dateFormat.parse(time);
                        cards.add(new BlockedCard(words[0], words[1], Double.parseDouble(words[2]), date));
                    }
                    else if(words.length == 3){
                        cards.add(new Card(words[0], words[1], Double.parseDouble(words[2])));
                    }
                }
            }
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean save(Card card){
        try{
            FileWriter fileWriter = new FileWriter(PATH,false);
            fileWriter.write(Double.toString(Atm.getMoneyLimit())+'\n');
            for(Card cardFromDB:getCards()){
                if(cardFromDB.getNumber().getValue().equals(card.getNumber().getValue())){
                    cardFromDB=card;
                }
                String cardData = cardFromDB.getNumber().getValue() + ' ' + Integer.toString(cardFromDB.getPinCode().getValue()) + ' ' + Double.toString(cardFromDB.getAccount().getMoneyCount());
                if(cardFromDB.getClass() == BlockedCard.class) {
                    cardData += (" " + ((BlockedCard) cardFromDB).getUnblockingTime() + "\n");
                }
                else{
                    cardData += "\n";
                }
                fileWriter.write(cardData);
            }
            fileWriter.flush();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Card dataGetting(Card card){
        for(Card cardFromDB:Data.getCards()){
            if(cardFromDB.getNumber().getValue().equals(card.getNumber().getValue())){
                try {
                    card = new Card(cardFromDB.getNumber().getValue(),cardFromDB.getPinCode().getValue(),cardFromDB.getAccount().getMoneyCount());
                    if(cardFromDB.getClass()==BlockedCard.class){
                        BlockedCard blockedCard = new BlockedCard(((BlockedCard) cardFromDB).getUnblockingTime(),card);
                        return blockedCard;
                    }
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
        return card;
    }
}
