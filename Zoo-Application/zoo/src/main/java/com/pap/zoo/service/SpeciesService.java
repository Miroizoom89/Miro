package com.pap.zoo.service;

import com.pap.zoo.entity.Species;
import com.pap.zoo.entity.Task;
import com.pap.zoo.repository.AnimalRepository;
import com.pap.zoo.repository.SpeciesRepository;
import com.pap.zoo.repository.TaskRepository;
import org.hibernate.tool.schema.spi.SchemaManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SpeciesService {

    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private TaskRepository taskRepository;

    //PUSH methods + validation

    public Pair<Boolean,String> saveSpecies(Species species) {
        try{
            if(speciesRepository.findBySpecies(species.getSpecies())!=null) {
                throw new SchemaManagementException("Ten gatunek już istnieje.");
            }
            if(speciesRepository.findByCage(species.getCage())!=null){
                throw new SchemaManagementException("Klatka jest zajęta przez inny gatunek.");
            }
            speciesRepository.save(species);
            return Pair.of(true,"");
        } catch(Exception ex) {
            return Pair.of(false,ex.getMessage());
        }
    }

    @Scheduled(cron =  "0 0 14 * * *")
    public void createFeedingTasks() {
        try {
            for (Species species : speciesRepository.findAll()) {
                Task task = new Task();
                task.setZooKeeper(null);
                task.setLogin(null);
                task.setCage(species.getCage());
                task.setSpecies(species);
                task.setDescription("Trzeba nakarmić " + species.getSpecies() + ".");
                task.setDuration(Time.valueOf("01:00:00"));
                LocalTime startTime = LocalTime.ofInstant(Instant.ofEpochMilli(species.getFeedingHour().getTime()), ZoneId.of("Europe/Warsaw"));
                LocalDateTime startDate = LocalDateTime.now().withHour(startTime.getHour()).withMinute(startTime.getMinute()).withSecond(0).withNano(0);
                task.setStartTime(startDate);
                taskRepository.save(task);
        }
        } catch (Exception ex) {
            System.out.println("Nie udało się zrealizować zadania.");
        }
    }

    //GET methods

    public List<Species> getSpecies() { return speciesRepository.findAll(); }

    public Species getSpeciesById(int id) {
        return speciesRepository.findById(id).orElse(null);
    }

    public Species getSpeciesByCage(String cage) {
        return speciesRepository.findByCage(cage);
    }

    public Species getAnimalBySpecies(String species) {
        return speciesRepository.findBySpecies(species);
    }

    public List<String> getSpeciesNames() {
        List<String> speciesNames = new ArrayList<>();
        speciesRepository.findAll().forEach(species -> speciesNames.add(species.getSpecies()));
        return speciesNames;
    }

    public List<String> getSpeciesCages() {
        List<String> speciesCages = new ArrayList<>();
        speciesRepository.findAll().forEach(species -> speciesCages.add(species.getCage()));
        return speciesCages;
    }

    //UPDATE methods + validation

    public Pair<Boolean,String> updateSpecies(Species species) {
        try {
            Species existingSpecies = speciesRepository.findById(species.getId()).orElse(null);
            assert existingSpecies != null;
            existingSpecies.setDescription(species.getDescription());
            existingSpecies.setCage(species.getCage());
            existingSpecies.setFeedingHour(species.getFeedingHour());
            existingSpecies.setSpecies(species.getSpecies());
            speciesRepository.save(existingSpecies);
            return Pair.of(true,"");
        } catch (Exception ex) {
            return Pair.of(false,"Ten gatunek już istnieje.");
        }
    }


    //DELETE methods + validation

    public Pair<Boolean,String> deleteSpecies(int id) {
        try {
            if(speciesRepository.findById(id).isPresent()) {
                if(animalRepository.findBySpecies(Objects.requireNonNull(speciesRepository.findById(id).orElse(null)).getSpecies()).isEmpty()) {
                    //if species deleted then its tasks are also deleted
                    for (Task task: taskRepository.findBySpeciesId(Objects.requireNonNull(speciesRepository.findById(id).orElse(null)).getId())) {
                        //delete task
                        taskRepository.delete(task);
                    }
                    speciesRepository.deleteById(id);
                    return Pair.of(true,"");
                }
                throw new SchemaManagementException("Nie można usunąc tego gatunku, istnieją zwierzęta tego gatunku.");
            }
            throw new SchemaManagementException("Gatunek o takim ID nie istnieje.");
        } catch (Exception ex) {
            return Pair.of(false,ex.getMessage());
        }
    }
}
