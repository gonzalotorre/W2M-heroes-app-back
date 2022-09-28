package com.heroes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.Mockito.verify;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = {W2mHeroesApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@AutoConfigureMockMvc
public class PutHeroMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeroesService heroesServiceMock;

    @Test
    @WithMockUser(username = "user", password = "pass", authorities = {"USER_ROLE"})
    public void putHero_200() throws Exception {
        final var heroDTO = new HeroDTO(1, "Spider-Man", "throws web");
        final var updatedHero = new HeroDTO(1, "Spideer-Man", "throows web");

        Mockito.when(this.heroesServiceMock.update(heroDTO)).thenReturn(updatedHero);

        this.mockMvc.perform(put("/update")
                        .with(csrf())
                        .content(asJsonString(heroDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andDo(print())
                .andExpect(jsonPath("$.name").value("Spideer-Man"))
                .andExpect(jsonPath("$.power").value("throows web"));

        verify(this.heroesServiceMock).update(heroDTO);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser(username = "user", password = "pass", authorities = {"USER_ROLE"})
    public void putHero_404() throws Exception {
        this.mockMvc.perform(put("/updat")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andDo(print());
    }

    @Test
    public void putHero_401() throws Exception {
        this.mockMvc.perform(put("/save")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(401))
                .andDo(print());

    }

}
