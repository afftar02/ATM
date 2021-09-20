package com.mycompany.atm.data;

import com.mycompany.atm.card.Card;

public interface DataProcessor {
    boolean read();

    boolean save(Card card);
}
