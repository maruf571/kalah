package com.maruf.kalah.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * This class represent the game. A game contain players, board, games status and updateTime time.
 *
 * @author maruf
 */

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Game{

    private String id;
    private Board board;
    private Player player1;
    private Player player2;
    private Player winner;
    private GameStatus gameStatus;
    private Long updateAt;

    public Game(Integer initialStoneOnPit) {
        this.player1 = new Player(Player.PLAYER1_INDEX, "player1");
        this.player2 = new Player(Player.PLAYER2_INDEX, "player2");
        this.board = new Board(initialStoneOnPit, player1, player2);
        this.gameStatus = GameStatus.INIT;
        this.updateAt = System.currentTimeMillis();
    }

    /**
     * This method is set the time of the last activity.
     */
    public void updateTime(){
        this.setUpdateAt(System.currentTimeMillis());
    }

}
