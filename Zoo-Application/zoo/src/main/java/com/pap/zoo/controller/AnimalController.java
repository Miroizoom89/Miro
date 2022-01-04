package com.pap.zoo.controller;

import com.pap.zoo.entity.Animal;
import com.pap.zoo.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animals")
@CrossOrigin
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping("/add")
    public Pair<Boolean,String> addAnimal(@RequestBody Animal animal) {
        return animalService.save(animal);
    }

    @GetMapping("/list")
    public List<Animal> findAllAnimals() { return animalService.getAnimals(); }

    @GetMapping("/animalById/{id}")
    public Animal findAnimalById(@PathVariable int id) { return animalService.getAnimalById(id); }

    @PutMapping("/update")///check
    public Pair<Boolean, String> updateAnimal(@RequestBody Animal animal) {
        return animalService.updateAnimal(animal);
    }

    @DeleteMapping("/delete/{id}")
    public Pair<Boolean, String> deleteAnimal(@PathVariable int id) { return animalService.delete(id); }

}
