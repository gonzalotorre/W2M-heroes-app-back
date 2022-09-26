package com.heroes.service;

import com.heroes.entity.Hero;
import com.heroes.repository.HeroesRepository;
import com.heroes.service.impl.HeroesServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
@DisplayName("Tests HeroesServiceImpl methods")
public class HeroesServiceImplTest {

    private @Mock HeroesRepository heroesRepositoryMock;

    private @InjectMocks HeroesServiceImpl heroesServiceMock;

    @Test
    void findAllHeroes() {

        final var listHeroes = this.generateHeroesList();

        when(this.heroesRepositoryMock.findAll()).thenReturn(listHeroes);

        final var result = this.heroesServiceMock.findAll();

        assertNotNull(result);

        verify(this.heroesRepositoryMock).findAll();

    }

    @Test
    void findHeroById() {

        final long id = 1;

        final Optional<Hero> hero = Optional.of(new Hero(1, "Name 1", "Power 1"));

        when(this.heroesRepositoryMock.findById(id)).thenReturn(hero);

        final var result = this.heroesServiceMock.findById(id);

        assertAll("",
                () -> assertNotNull(result),
                () -> assertEquals(hero.get(), result)
        );

        verify(this.heroesRepositoryMock).findById(id);

    }

    @Test
    void findByWord() {

        final var listHeroes = this.generateHeroesList();

        final var expectedResult = new ArrayList<Hero>();
        expectedResult.add(new Hero(1, "Spider-Man", "throw webs"));
        expectedResult.add(new Hero(2, "Superman", "Flight, superhuman strength, x-ray vision"));

        final var word = "man";

        when(this.heroesRepositoryMock.findAll()).thenReturn(listHeroes);

        final var result = this.heroesServiceMock.findByCharacter(word);

        assertAll("",
                () -> assertNotNull(result),
                () -> assertEquals(expectedResult, result)
        );

        verify(this.heroesRepositoryMock).findAll();

    }

    @Test
    void saveHero() {

        final var hero = new Hero(1, "Name 1", "Power 1");

        when(this.heroesRepositoryMock.save(hero)).thenReturn(hero);

        final var result = this.heroesServiceMock.save(hero);

        assertAll("",
                () -> assertNotNull(result),
                () -> assertEquals(1, result)
        );

        verify(this.heroesRepositoryMock).save(hero);

    }

    @Test
    void updateHero() {

        /*final var hero = new Hero(1, "Name 1", "Power 1");
        final var updateHero = new Hero(1, "Naaame 1", "Power 1");

        when(this.heroesRepositoryMock.findById(hero.getId())).thenReturn(Optional.of(hero));
        when(this.heroesRepositoryMock.save(hero)).thenReturn(updateHero);

        final var result = this.heroesServiceMock.update(updateHero);

        assertAll("",
                () -> assertNotNull(result),
                () -> assertEquals(updateHero, result)
        );

        verify(this.heroesRepositoryMock).findById(hero.getId());
        verify(this.heroesRepositoryMock).save(hero);*/

    }

    @Test
    void deleteHero() {

        final long id = 1;

        doNothing().when(this.heroesRepositoryMock).deleteById(id);

        this.heroesServiceMock.delete(id);

        verify(this.heroesRepositoryMock).deleteById(id);

    }

    private List<Hero> generateHeroesList() {
        final var list = new ArrayList<Hero>();
        list.add(new Hero(1, "Spider-Man", "throw webs"));
        list.add(new Hero(2, "Superman", "Flight, superhuman strength, x-ray vision"));
        list.add(new Hero(3, "Hulk", "Super strength"));
        list.add(new Hero(4, "Black Panther", "Juices of the Heart-Shaped Herb"));
        return list;
    }

}
