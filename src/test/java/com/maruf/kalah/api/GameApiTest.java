package com.maruf.kalah.api;

import com.maruf.kalah.model.Game;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GameApiTest {

    private static final Integer INITIAL_STONE_ON_PIT = 6;
    private static final Integer INITIAL_STONE_ON_HOUSE = 0;
    private static final Integer PLAYER1_INDEX = 1;
    private static final Integer PLAYER2_INDEX = 2;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @PostConstruct
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    @DirtiesContext
    public void shouldInitGame() throws Exception {

        MockHttpServletRequestBuilder initGameRequest = MockMvcRequestBuilders.post("/api/kalah/games");

        mockMvc.perform(initGameRequest)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())

                //check game state
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameStatus").value("INIT"))

                //check total pit size
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.size()", Matchers.is(14)))

                //check pit index
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.1.pitIndex").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.8.pitIndex").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.14.pitIndex").value(14))

                //check player index
                .andExpect(MockMvcResultMatchers.jsonPath("$.player1.playerIndex").value(GameApiTest.PLAYER1_INDEX))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player2.playerIndex").value(GameApiTest.PLAYER2_INDEX))

                //check pit owner
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.6.playerIndex").value(GameApiTest.PLAYER1_INDEX))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.13.playerIndex").value(GameApiTest.PLAYER2_INDEX))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.14.playerIndex").value(GameApiTest.PLAYER2_INDEX))

                //check  initial pit stone count
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.5.stoneCount").value(GameApiTest.INITIAL_STONE_ON_PIT))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.12.stoneCount").value(GameApiTest.INITIAL_STONE_ON_PIT))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.14.stoneCount").value(GameApiTest.INITIAL_STONE_ON_HOUSE))

                .andReturn();
    }


    @Test
    @DirtiesContext
    public void shouldPlayGame() throws Exception {

        MockHttpServletRequestBuilder initGameRequest = MockMvcRequestBuilders.post("/api/kalah/games");
        String responseString = mockMvc.perform(initGameRequest).andReturn().getResponse().getContentAsString();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Game game = objectMapper.readValue(responseString, Game.class);


        MockHttpServletRequestBuilder playGame = MockMvcRequestBuilders.put("/api/kalah/games/"+game.getId()+"/pits/"+ 1);

        mockMvc.perform(playGame)
                .andExpect(MockMvcResultMatchers.status().isOk())

                //check game id
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(game.getId()))

                //check total pit size
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.size()", Matchers.is(14)))

                //check player index
                .andExpect(MockMvcResultMatchers.jsonPath("$.player1.playerIndex").value(GameApiTest.PLAYER1_INDEX))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player2.playerIndex").value(GameApiTest.PLAYER2_INDEX))


                //starting pit should be zero
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.1.stoneCount").value(0))

                //pit should increment by 1
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.2.stoneCount").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.3.stoneCount").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.4.stoneCount").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.5.stoneCount").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.6.stoneCount").value(7))

                //player 1 house should increment by 1
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.7.stoneCount").value(1))

                //check game state as end with player 1 house
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameStatus").value("PLAYER1_TURN"))

                .andReturn();

    }

}
