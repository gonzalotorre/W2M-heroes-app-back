package com.heroes.controller;

import com.heroes.dto.HeroDTO;
import com.heroes.entity.Hero;
import com.heroes.mapper.HeroesMapper;
import com.heroes.service.HeroesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class HeroesController {

    private final HeroesService heroesService;

    @GetMapping("/findAll")
    public List<Hero> findAll() {
        log.info("--> Getting all heroes.");
        return this.heroesService.findAll();
    }

    @GetMapping("/id/{heroId}")
    public Hero findById(@PathVariable("heroId") long heroId) {
        log.info("--> Finding hero by id ");
        return this.heroesService.findById(heroId);
    }

    @GetMapping("/word/{heroWord}")
    public List<Hero> findByCharacter(@PathVariable("heroWord") String heroWord) {
        log.info("--> Finding all heroes whose name contains character");
        return this.heroesService.findByCharacter(heroWord);
    }

    @PostMapping("/save")
    public long add(@RequestBody Hero newHero) {
        return this.heroesService.save(newHero);
    }

    @PutMapping("/update")
    public Hero update(@RequestBody HeroDTO updateHero) {
        return this.heroesService.update(updateHero);
    }

    @DeleteMapping("/delete/{heroId}")
    public void delete(@PathVariable("heroId") long heroId) {
        this.heroesService.delete(heroId);
    }


}
