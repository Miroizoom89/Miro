package com.pap.zoo.service;

import com.pap.zoo.entity.Animal;
import com.pap.zoo.repository.AnimalRepository;
import com.pap.zoo.repository.SpeciesRepository;
import org.hibernate.tool.schema.spi.SchemaManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private SpeciesRepository speciesRepository;

    //PUSH methods + validation

    public Pair<Boolean,String> save(Animal animal) {
        try{
            if(speciesRepository.findBySpecies(animal.getSpecies()) != null) {
                animal.setSpeciesInfo(speciesRepository.findBySpecies(animal.getSpecies()));
                animalRepository.save(animal);
            }
            else {
                throw new SchemaManagementException("Podany gatunek jest niepoprawny.");
            }
        } catch (SchemaManagementException ex) {
            return Pair.of(false,ex.getMessage());
        }
        return Pair.of(true,"");
    }

    //GET methods

    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }

    public Animal getAnimalById(int id) {
        return animalRepository.findById(id).orElse(null);
    }

    //UPDATE methods + validation

    public Pair<Boolean,String> updateAnimal(Animal animal) {
        try{
            if(speciesRepository.findBySpecies(animal.getSpecies())!=null){
                Animal existingAnimal = animalRepository.findById(animal.getId()).orElse(null);
                assert existingAnimal != null;
                existingAnimal.setName(animal.getName());
                existingAnimal.setDob(animal.getDob());
                if(!existingAnimal.getSpecies().equals(animal.getSpecies())){
                    existingAnimal.setSpeciesInfo(speciesRepository.findBySpecies(animal.getSpecies()));
                }
                existingAnimal.setSpecies(animal.getSpecies());
                animalRepository.save(existingAnimal);
                return Pair.of(true,"");
            } else {
                throw new SchemaManagementException("Podany gatunek jest błędny.");
            }
        } catch (Exception ex) {
            return Pair.of(false, ex.getMessage());
        }
    }

    //DELETE methods + validation

    public Pair<Boolean,String> delete(int id) {
        try {
            if(animalRepository.findById(id).isEmpty()) {
                throw new SchemaManagementException("Nie ma zwierzęcie o takim ID.");
            }
            animalRepository.deleteById(id);
            return Pair.of(true,"");
        } catch(Exception ex) {
            return Pair.of(false, ex.getMessage());
        }
    }
}
