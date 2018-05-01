package com.maruf.kalah.service;


import com.maruf.kalah.model.Game;

public interface GameService {

    Game initGame(Integer pitNumber);

    Game play(String gameId, Integer pitId);
}
