package com.mycompany.cash_machine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Data implements Saver{
    static ArrayList<Card> cards= new ArrayList<Card>();

    public void read(){
        try {
            FileReader fileReader = new FileReader("Data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            CashMachine.moneyLimit = Double.parseDouble(line);
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
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void save(Card card){
        try{
            FileWriter fileWriter = new FileWriter("Data.txt",false);
            fileWriter.write(Double.toString(CashMachine.moneyLimit)+'\n');
            for(Card cardFromDB:cards){
                if(cardFromDB.number.value.equals(card.number.value)){
                    cardFromDB=card;
                }
                String cardData = cardFromDB.number.value + ' ' + Integer.toString(cardFromDB.pinCode.value) + ' ' + Double.toString(cardFromDB.account.moneyCount);
                if(cardFromDB.getClass() == BlockedCard.class) {
                    cardData += (" " + ((BlockedCard) cardFromDB).unblockingTime + "\n");
                }
                else{
                    cardData += "\n";
                }
                fileWriter.write(cardData);
            }
            fileWriter.flush();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static Card dataGetting(Card card){
        for(Card cardFromDB:Data.cards){
            if(cardFromDB.number.value.equals(card.number.value)){
                try {
                    card.number.value = cardFromDB.number.value;
                    card.account.moneyCount = cardFromDB.account.moneyCount;
                    card.pinCode.value = cardFromDB.pinCode.value;
                    if(cardFromDB.getClass()==BlockedCard.class){
                        BlockedCard blockedCard = new BlockedCard(card);
                        blockedCard.unblockingTime=((BlockedCard) cardFromDB).unblockingTime;
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
