package com.heroes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/hero")
public class HeroController {

    @GetMapping("/findAll")
    public String findAll() {
        log.info("--> Getting all heroes.");
        return "Find All Heroes";
    }

    @GetMapping("/id")
    public String findById() {
        log.info("--> Finding hero by id ");
        return "Find hero by ID";
    }

    @GetMapping("/word")
    public String findByCharacter() {
        log.info("--> Finding all heroes whose name contains character");
        return "Find hero by a character";
    }

    @PostMapping("/add")
    public long add() {
        return 0;
    }

    @PutMapping("/update")
    public String update() {
        return "Update hero";
    }

    @DeleteMapping("/delete")
    public void delete() {

    }


}
