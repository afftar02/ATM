package com.mycompany.atm.control;

import com.mycompany.atm.view.View;
import com.mycompany.atm.card.BlockedCard;
import com.mycompany.atm.card.Card;

import java.util.Date;
import java.util.Calendar;

public abstract class BlockingControl {
    private static Date taskDate;
    private static Date currentDate;

    public static Date getTaskDate(){
        return taskDate;
    }
    private static void setTaskDate(Date value){
        taskDate = value;
    }

    public static Date getCurrentDate(){
        return currentDate;
    }
    private static void setCurrentDate(Date value){
        currentDate = value;
    }

    public static BlockedCard block(Card card){
        setTaskDate(new Date());
        Calendar unblockTime = Calendar.getInstance();
        unblockTime.setTime(taskDate);
        unblockTime.add(Calendar.DAY_OF_YEAR,1);
        setTaskDate(unblockTime.getTime());
        View.blockNotification(card.getNumber().getValue());
        BlockedCard blockedCard = new BlockedCard(getTaskDate(),card);
        return blockedCard;
    }

    public static boolean isBlockTimeOver(BlockedCard blockedCard){
        setCurrentDate(new Date());
        if(getCurrentDate().equals(blockedCard.getUnblockingTime()) || getCurrentDate().after(blockedCard.getUnblockingTime())){
            return true;
        }
        else{
            return false;
        }
    }

    public static Card unblock(BlockedCard blockedCard){
        Card card = new Card(blockedCard);
        View.unblockNotification();
        return card;
    }
}
