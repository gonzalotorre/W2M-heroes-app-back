package com.heroes.mapper;

import com.heroes.dto.HeroDTO;
import com.heroes.entity.Hero;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface HeroesMapper {

    Hero heroDtoToHero(HeroDTO heroDTO);

    HeroDTO heroToDto(Hero hero);

    List<HeroDTO> listHeroToListDto(List<Hero> heroes);

    List<Hero> listDtoToListHero(List<HeroDTO> heroesDTO);

}
