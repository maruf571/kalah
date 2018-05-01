package com.maruf.kalah.service;

import com.maruf.kalah.model.Game;
import com.maruf.kalah.repository.GameMemoryRepository;
import com.maruf.kalah.model.Pit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * This class works as bridge between controller and game engine.
 *
 * @author maruf
 */

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    private final GameMemoryRepository gameMemoryRepository;
    private final GameEngine gameEngine;
    public GameServiceImpl(GameMemoryRepository gameMemoryRepository, GameEngine gameEngine) {
        this.gameMemoryRepository = gameMemoryRepository;
        this.gameEngine = gameEngine;
    }

    /**
     * This method is responsible to initialize new game
     *
     * @param initialPitStoneCount is the initial number of stone.
     * @return Game
     */
    @Override
    public Game initGame(Integer initialPitStoneCount) {
        return gameMemoryRepository.create(initialPitStoneCount);
    }


    /**
     * This method is responsible for every new move of the stones from a pit.
     *
     * @param gameId game id
     * @param pitIndex index of the pit
     * @return Game
     */
    @Override
    public Game play(String gameId, Integer pitIndex) {
        log.debug("gameId {}, pitIndex {}",gameId, pitIndex);

        Game game = gameMemoryRepository.findById(gameId);
        Pit pit = game.getBoard().getPitByPitIndex(pitIndex);

        gameEngine.play(game, pit);
        return  game;
    }

}
