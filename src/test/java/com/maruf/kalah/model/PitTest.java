package com.maruf.kalah.model;

import org.junit.Assert;
import org.junit.Test;

public class PitTest {

    @Test
    public void shouldDistributable(){

        //given
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit7 = new Pit(7, 6, 1);

        //then
        Assert.assertEquals(true, pit1.isDistributable(GameStatus.PLAYER1_TURN));
        Assert.assertEquals(false, pit7.isDistributable(GameStatus.PLAYER2_TURN));
    }

    @Test
    public void shouldPlayerPit(){

        //given
        Pit pit1 = new Pit(1, 6, 1);

        //then
        Assert.assertEquals(true, pit1.isPlayerPit(GameStatus.PLAYER1_TURN));
        Assert.assertEquals(false, pit1.isPlayerPit(GameStatus.PLAYER2_TURN));
    }

    @Test
    public void shoudHouse(){
        //given
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit7 = new Pit(7, 6, 1);
        Pit pit13 = new Pit(13, 6, 1);
        Pit pit14 = new Pit(14, 6, 1);

        //then
        Assert.assertEquals(false, pit1.isHouse());
        Assert.assertEquals(true, pit7.isHouse());
        Assert.assertEquals(false, pit13.isHouse());
        Assert.assertEquals(true, pit14.isHouse());
    }

    @Test
    public void shouldNextPitIndex(){

        //given
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit7 = new Pit(7, 6, 1);
        Pit pit14 = new Pit(14, 6, 1);

        //then
        Assert.assertEquals(Integer.valueOf(2), pit1.nextPitIndex());
        Assert.assertEquals(Integer.valueOf(8), pit7.nextPitIndex());
        Assert.assertEquals(Integer.valueOf(1), pit14.nextPitIndex());


    }

    @Test
    public void shouldPlayer1House(){

        //given
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit7 = new Pit(7, 6, 1);
        Pit pit8 = new Pit(8, 6, 2);
        Pit pit14 = new Pit(14, 6, 2);

        Assert.assertEquals(false, pit1.isPlayer1House());
        Assert.assertEquals(true, pit7.isPlayer1House());
        Assert.assertEquals(false, pit8.isPlayer1House());
        Assert.assertEquals(false, pit14.isPlayer1House());

    }

    @Test
    public void shouldPlayer2House(){

        //given
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit7 = new Pit(7, 6, 1);
        Pit pit8 = new Pit(8, 6, 2);
        Pit pit14 = new Pit(14, 6, 2);

        Assert.assertEquals(false, pit1.isPlayer2House());
        Assert.assertEquals(false, pit7.isPlayer2House());
        Assert.assertEquals(false, pit8.isPlayer2House());
        Assert.assertEquals(true, pit14.isPlayer2House());
    }

    @Test
    public void shouldGetOppositePitIndex(){

        //given
        Pit pit1 = new Pit(1, 6, 1);
        Pit pit8 = new Pit(8, 6, 1);

        //then
        Assert.assertEquals(Integer.valueOf(13), pit1.getOppositePitIndex());
        Assert.assertEquals(Integer.valueOf(6), pit8.getOppositePitIndex());

    }
}
