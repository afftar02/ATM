package com.mycompany.cash_machine;

import java.util.Date;
import java.util.Calendar;

public abstract class BlockingControl {
    private static Date taskDate;
    private static Date currentDate;

    public static BlockedCard block(Card card){
        taskDate=new Date();
        Calendar unblockTime = Calendar.getInstance();
        unblockTime.setTime(taskDate);
        unblockTime.add(Calendar.DAY_OF_YEAR,1);
        taskDate=unblockTime.getTime();
        View.blockNotification(card.number.value);
        BlockedCard blockedCard = new BlockedCard(card);
        blockedCard.unblockingTime = taskDate;
        return blockedCard;
    }

    public static boolean isBlockTimeOver(BlockedCard blockedCard){
        currentDate=new Date();
        if(currentDate.equals(blockedCard.unblockingTime) || currentDate.after(blockedCard.unblockingTime)){
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
