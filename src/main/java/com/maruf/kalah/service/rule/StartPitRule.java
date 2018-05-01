package com.maruf.kalah.service.rule;

import com.maruf.kalah.exception.KalahIllegalMoveException;
import com.maruf.kalah.model.*;
import lombok.extern.slf4j.Slf4j;

/**
 * This class check starting rules for the distributing stones.
 *
 * @author maruf
 */
@Slf4j
public class StartPitRule extends KalahRule {

    @Override
    public void apply(Game game, Pit startPit) {
        log.debug("check rules for the start pit {}", startPit);

        checkPlayerTurnRule(game, startPit);
        checkEmptyStartRULE(startPit);
        this.next.apply(game, startPit);
    }

    private void checkPlayerTurnRule(Game game, Pit startPit){

        if(game.getGameStatus().equals(GameStatus.INIT)) {
            GameStatus gameStatus =  startPit.getPlayerIndex().equals(Player.PLAYER1_INDEX) ? GameStatus.PLAYER1_TURN : GameStatus.PLAYER2_TURN;
            game.setGameStatus(gameStatus);
        }

        if((game.getGameStatus().equals(GameStatus.PLAYER1_TURN) && startPit.getPitIndex() >= Board.PLAYER1_HOUSE) ||
                (game.getGameStatus().equals(GameStatus.PLAYER2_TURN) && startPit.getPitIndex() <= Board.PLAYER1_HOUSE)){
            throw new KalahIllegalMoveException("Incorrect pit to play");
        }
    }

    private void checkEmptyStartRULE(Pit startPit){

        if(startPit.getStoneCount() == 0){
            throw new KalahIllegalMoveException("Can not start from empty pit");
        }
    }
}
