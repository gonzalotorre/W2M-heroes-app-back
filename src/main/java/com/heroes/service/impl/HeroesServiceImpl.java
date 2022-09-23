package com.heroes.service.impl;

import com.heroes.dto.HeroeDTO;
import com.heroes.entity.Hero;
import com.heroes.repository.HeroesRepository;
import com.heroes.service.HeroesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class HeroesServiceImpl implements HeroesService {

    private final HeroesRepository heroesRepository;

    @Override
    public List<Hero> findAll() {
        final var heroes = this.heroesRepository.findAll();
        log.info("Found {} heroes.", heroes.size());
        return heroes;
    }

    @Override
    public Hero findById(long id) {
        final var resp = this.heroesRepository.findById(id);
        final Hero hero = (resp.isPresent()) ? resp.get() : null;
        log.info("Found hero {}", hero);
        return hero;
    }

    @Override
    public List<Hero> findByCharacter(String word) {
        final var heroes = this.heroesRepository.findAll();
        final List<Hero> result = heroes.stream()
                .filter(hero -> hero.getName().toLowerCase(Locale.ROOT).contains(word.toLowerCase()))
                .collect(Collectors.toList());
        log.info("Found {} heroes that contains the word {}.", result.size(), word);
        return result;
    }

    @Override
    public long save(Hero newHero) {
        final var hero = this.heroesRepository.save(newHero);
        log.info("Saved new hero {}", hero);
        return hero.getId();
    }

    @Override
    public Hero update(HeroeDTO updateHero) {
        final var resp = this.heroesRepository.findById(updateHero.getId());
        final Hero hero = (resp.isPresent()) ? resp.get() : null;
        hero.setName(updateHero.getName());
        hero.setPower(updateHero.getPower());
        log.info("Updated hero {}", resp.get());
        return this.heroesRepository.save(hero);
    }

    @Override
    public void delete(long id) {
        this.heroesRepository.deleteById(id);
        log.info("Deleted hero with id {}", id);
    }

}
