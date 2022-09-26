package com.heroes.service;

import com.heroes.dto.HeroDTO;
import com.heroes.entity.Hero;

import java.util.List;

public interface HeroesService {

    /**
     * Get all heroes.
     * @return a list of all heroes in database.
     */
    List<HeroDTO> findAll();

    /**
     * Get a hero by id.
     * @param id of the hero to search.
     * @return the hero with this id.
     */
    HeroDTO findById(long id);

    /**
     * Get all heroes that contains a string.
     *
     * @param word to search in all heroes.
     * @return a list of heroes that contains that word.
     */
    List<HeroDTO> findByCharacter(String word);

    /**
     * Add a hero to database.
     * @param newHero object to save in database.
     * @return the id of the hero.
     */
    long save(HeroDTO newHero);

    /**
     * Update a hero of the database.
     * @param updateHero hero to update in database.
     * @return the updated hero.
     */
    HeroDTO update(HeroDTO updateHero);


    /**
     * Delete a hero of the database;
     * @param id of hero to delete.
     */
    void delete(long id);

}
