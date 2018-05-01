package com.maruf.kalah.model;

import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    @Test
    public void shouldCreateGame(){

        //give
        Game game = new Game(Board.INITIAL_STONE_ON_PIT);

        //then
        Assert.assertEquals(Player.PLAYER1_INDEX, game.getPlayer1().getPlayerIndex());
        Assert.assertEquals(Player.PLAYER2_INDEX, game.getPlayer2().getPlayerIndex());
        Assert.assertNotNull(game.getBoard());
        Assert.assertEquals(14, game.getBoard().getPits().size());
        Assert.assertEquals(GameStatus.INIT, game.getGameStatus());
    }


}
