package com.heroes.repository;

import com.heroes.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroesRepository extends JpaRepository<Hero, Long> {
}
