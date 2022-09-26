package com.heroes.controller;

import com.heroes.dto.HeroDTO;
import com.heroes.entity.Hero;
import com.heroes.mapper.HeroesMapper;
import com.heroes.service.HeroesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HeroesController {

    @Autowired
    private HeroesService heroesService;

    @GetMapping("/findAll")
    public List<HeroDTO> findAll() {
        return this.heroesService.findAll();
    }

    @GetMapping("/id/{heroId}")
    public HeroDTO findById(@PathVariable("heroId") long heroId) {
        return this.heroesService.findById(heroId);
    }

    @GetMapping("/word/{heroWord}")
    public List<HeroDTO> findByCharacter(@PathVariable("heroWord") String heroWord) {
        return this.heroesService.findByCharacter(heroWord);
    }

    @PostMapping("/save")
    public long add(@RequestBody HeroDTO newHero) {
        return this.heroesService.save(newHero);
    }

    @PutMapping("/update")
    public HeroDTO update(@RequestBody HeroDTO updateHero) {
        return this.heroesService.update(updateHero);
    }

    @DeleteMapping("/delete/{heroId}")
    public void delete(@PathVariable("heroId") long heroId) {
        this.heroesService.delete(heroId);
    }


}
