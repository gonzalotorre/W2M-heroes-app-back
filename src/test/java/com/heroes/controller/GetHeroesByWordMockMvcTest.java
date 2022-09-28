package com.heroes.controller;

import com.heroes.W2mHeroesApplication;
import com.heroes.dto.HeroDTO;
import com.heroes.service.HeroesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = {W2mHeroesApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@AutoConfigureMockMvc
public class GetHeroesByWordMockMvcTest {

    private static final String WORD = "man";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeroesService heroesServiceMock;

    @Test
    @WithMockUser(username = "user", password = "pass", authorities = {"USER_ROLE"})
    public void getHeroesByCharacter_200() throws Exception {

        final var expectedResultDTO = new ArrayList<HeroDTO>();
        expectedResultDTO.add(new HeroDTO(1, "Spider-Man", "throw webs"));
        expectedResultDTO.add(new HeroDTO(2, "Superman", "Flight, superhuman strength, x-ray vision"));

        Mockito.when(this.heroesServiceMock.findByCharacter(WORD)).thenReturn(expectedResultDTO);

        this.mockMvc.perform(get("/word/{heroWord}", WORD)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andDo(print())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$").isNotEmpty());

        verify(this.heroesServiceMock).findByCharacter(WORD);
    }

    @Test
    @WithMockUser(username = "user", password = "pass", authorities = {"USER_ROLE"})
    public void getHeroesByCharacter_404() throws Exception {
        this.mockMvc.perform(get("/wor/{heroWord}", WORD)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andDo(print());
    }

    @Test
    public void getHeroesByCharacter_401() throws Exception {
        this.mockMvc.perform(get("/word/{heroWord}", WORD)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(401))
                .andDo(print());

    }

}
