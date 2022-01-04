package com.pap.zoo.repository;

import com.pap.zoo.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {

    Species findByCage(String cage);

    Species findBySpecies(String species);

}
