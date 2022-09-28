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

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = {W2mHeroesApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@AutoConfigureMockMvc
public class DeleteHeroMockMvcTest {

    private static final long HERO_ID = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeroesService heroesServiceMock;

    @Test
    @WithMockUser(username = "user", password = "pass", authorities = {"USER_ROLE"})
    public void getHeroById_200() throws Exception {

        doNothing().when(this.heroesServiceMock).delete(HERO_ID);

        this.mockMvc.perform(delete("/delete/{heroId}", HERO_ID)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andDo(print());

        verify(this.heroesServiceMock).delete(HERO_ID);
    }

    @Test
    @WithMockUser(username = "user", password = "pass", authorities = {"USER_ROLE"})
    public void deleteHeroById_500() throws Exception {

        doThrow(new RuntimeException()).when(this.heroesServiceMock).delete(HERO_ID);

        this.mockMvc.perform(delete("/delete/{heroId}", HERO_ID)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(500))
                .andDo(print());

        verify(this.heroesServiceMock).delete(HERO_ID);
    }

    @Test
    @WithMockUser(username = "user", password = "pass", authorities = {"USER_ROLE"})
    public void deleteHeroById_404() throws Exception {
        this.mockMvc.perform(delete("/delet/{heroId}", HERO_ID)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andDo(print());
    }

    @Test
    public void deleteHeroById_401() throws Exception {
        this.mockMvc.perform(delete("/delete/{heroId}", HERO_ID)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(401))
                .andDo(print());
    }

}
