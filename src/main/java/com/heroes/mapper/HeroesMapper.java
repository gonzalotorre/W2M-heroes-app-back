package com.heroes.mapper;

import com.heroes.dto.HeroDTO;
import com.heroes.entity.Hero;

import java.util.List;

public interface HeroesMapper {

    Hero heroDtoToHero(HeroDTO heroDTO);

    HeroDTO heroToDto(Hero heroe);

    List<HeroDTO> listHeroToListDto(List<Hero> heroes);

    List<Hero> listDtoToListHero(List<HeroDTO> heroesDTO);

}
