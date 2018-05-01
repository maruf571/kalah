package com.maruf.kalah.model;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * This class represent the player of the game.
 *
 * @author maruf
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Player{

    public static final Integer PLAYER1_INDEX = 1;
    public static final Integer PLAYER2_INDEX = 2;

    @NotNull
    private Integer playerIndex;

    @NotNull
    private String name;
}
