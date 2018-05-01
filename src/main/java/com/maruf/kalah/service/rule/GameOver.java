package com.maruf.kalah.service.rule;

import com.maruf.kalah.model.Board;
import com.maruf.kalah.model.Game;
import com.maruf.kalah.model.GameStatus;
import com.maruf.kalah.model.Pit;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is responsible to check the game end rules.
 *
 * @author maruf
 */
@Slf4j
public class GameOver extends KalahRule {


    @Override
    public void apply(Game game, Pit currentPit) {
        log.debug("checking game end rule");

        Integer player1StoneCount = game.getBoard().getPlayer1PitStoneCount();
        Integer player2StoneCount = game.getBoard().getPlayer2PitStoneCount();

        if( player1StoneCount == 0 || player2StoneCount == 0){

            game.setGameStatus(GameStatus.FINISHED);

            Pit house1 = game.getBoard().getPits().get(Board.PLAYER1_HOUSE);
            house1.setStoneCount(house1.getStoneCount() + player1StoneCount);

            Pit house2 = game.getBoard().getPits().get(Board.PLAYER2_HOUSE);
            house2.setStoneCount(house2.getStoneCount() + player2StoneCount);

            determineResult(game, house1.getStoneCount(), house2.getStoneCount());

            resetBoard(game);

        }
    }

    private void resetBoard(Game game){
        for(Integer key: game.getBoard().getPits().keySet()){
            if(key.equals(Board.PLAYER1_HOUSE) || key.equals(Board.PLAYER2_HOUSE)) {
                continue;
            }
            Pit pit = game.getBoard().getPits().get(key);
            pit.setStoneCount(0);
        }
    }

    private void determineResult(Game game, Integer house1StoneCount, Integer house2StoneCount){
        if(house1StoneCount > house2StoneCount){
            game.setWinner(game.getPlayer1());
        }else if(house1StoneCount < house2StoneCount){
            game.setWinner(game.getPlayer2());
        }else{
            game.setWinner(null);
        }
    }
}
