package com.maruf.kalah.service.rule;

import com.maruf.kalah.model.Game;
import com.maruf.kalah.model.Pit;

public abstract class KalahRule {

    protected KalahRule next;
    public abstract void apply(Game game, Pit currentPit);

    public KalahRule setNext(KalahRule next) {
        this.next = next;
        return next;
    }

}
