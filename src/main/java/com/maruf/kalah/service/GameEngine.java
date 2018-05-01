package com.maruf.kalah.service;

import com.maruf.kalah.model.Game;
import com.maruf.kalah.model.Pit;
import com.maruf.kalah.service.rule.*;
import org.springframework.stereotype.Component;

/**
 * This class represent the game rule chain.
 *
 * @author maruf
 */
@Component
public class GameEngine {

    private final KalahRule chain;
    public GameEngine() {

        this.chain = new StartPitRule();
        chain.setNext(new DistributePitStoneRule())
                .setNext(new EndPitRule())
                .setNext(new GameOver());
    }

    public void play(Game game, Pit pit) {
        this.chain.apply(game, pit);
    }

}
