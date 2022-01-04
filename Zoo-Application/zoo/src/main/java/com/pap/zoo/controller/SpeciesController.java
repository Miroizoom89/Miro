package com.pap.zoo.controller;

import com.pap.zoo.entity.Species;
import com.pap.zoo.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/species")
@CrossOrigin
public class SpeciesController {

    @Autowired
    private SpeciesService speciesService;

    @PostMapping("/add")
    public Pair<Boolean, String> addSpecies(@RequestBody Species species) { return speciesService.saveSpecies(species); }

    @GetMapping("/list")
    public List<Species> findAllSpecies() { return speciesService.getSpecies(); }

    @GetMapping("/speciesByCage/{cage}")
    public Species findSpeciesByCage(@PathVariable String cage) {return speciesService.getSpeciesByCage(cage); }

    @GetMapping("/speciesById/{id}")
    public Species findSpeciesById(@PathVariable int id) { return speciesService.getSpeciesById(id); }

    @GetMapping("/speciesBySpeciesName/{species}")
    public Species findSpeciesByName(@PathVariable String species) { return speciesService.getAnimalBySpecies(species); }

    @GetMapping("/speciesList")
    public List<String> findSpeciesNames() {return speciesService.getSpeciesNames(); }

    @GetMapping("/speciesCageList")
    public List<String> findSpeciesCage() {return speciesService.getSpeciesCages(); }

    @PutMapping("/update")///check
    public Pair<Boolean, String> updateSpecies(@RequestBody Species species) {
        return speciesService.updateSpecies(species);
    }

    @DeleteMapping("/delete/{id}")
    public Pair<Boolean, String> deleteSpecies(@PathVariable int id) { return speciesService.deleteSpecies(id); }

}
