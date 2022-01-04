package com.pap.zoo.service;


import com.pap.zoo.entity.Task;
import com.pap.zoo.entity.ZooKeeper;
import com.pap.zoo.repository.*;
import org.hibernate.tool.schema.spi.SchemaManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ZooKeeperRepository zooKeeperRepository;

    @Autowired
    private UserRepository userRepository;

    //PUSH methods + validation

    public Pair<Boolean,String> saveTask(Task task) {

        try{
            taskValidation(task);
            task.setSpecies(speciesRepository.findByCage(task.getCage()));
            assert(speciesRepository.findByCage(task.getCage())!=null);
            task.setZooKeeper(zooKeeperRepository.findByLogin(task.getLogin()));
            taskRepository.save(task);
            return Pair.of(true,"");
        } catch (Exception ex) {
            return Pair.of(false, ex.getMessage()+", dostępni użytkownicy = "+(listWithoutBrackets(listOfAvailableZooKeepers(task))));
        }
    }

    private void taskValidation(Task task) {
        if(speciesRepository.findByCage(task.getCage())==null) {
            throw new SchemaManagementException("Podana klataka jest nieprawidłowa.");
        }
        if(zooKeeperRepository.findByLogin(task.getLogin())==null) {
            throw new SchemaManagementException("Podany login jest nieprawidłowy");
        }
        if(!userRepository.findByLogin(zooKeeperRepository.findByLogin(task.getLogin()).getLogin()).getRole().equals("USER")) {
            throw new SchemaManagementException("Podany użytkownik nie jest pracownikiem");
        }
        if(animalRepository.findBySpecies(speciesRepository.findByCage(task.getCage()).getSpecies()).isEmpty()) {
            throw new SchemaManagementException("Nie ma zwierząt o podanym gatunku");
        }
        chekIfZooKeeperIsAvailable(task.getLogin(), task);
    }

    public void chekIfZooKeeperIsAvailable(String zooKeeperLogin, Task task) {
        long tzOffset = 3600000; //it is obligatory in order to set the right timeZone
        Instant instant = task.getStartTime().atZone(ZoneId.systemDefault()).toInstant();
        long startTimeInMillis = instant.toEpochMilli();
        long endTimeInMillis = startTimeInMillis + task.getDuration().getTime() + tzOffset;
        LocalDateTime endTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(endTimeInMillis), ZoneId.systemDefault());
        for (Task zooKeeperTask: taskRepository.findByLogin(zooKeeperLogin)) {
            Instant instantTask = zooKeeperTask.getStartTime().atZone(ZoneId.systemDefault()).toInstant();
            long sTimeInMillis = instantTask.toEpochMilli();
            long eTimeInMillis = sTimeInMillis + zooKeeperTask.getDuration().getTime() + tzOffset;
            LocalDateTime eTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(eTimeInMillis), ZoneId.systemDefault());
            if(task.getStartTime().getDayOfMonth()==zooKeeperTask.getStartTime().getDayOfMonth() && task.getStartTime().getMonth()==zooKeeperTask.getStartTime().getMonth() && task.getStartTime().getYear()==zooKeeperTask.getStartTime().getYear()) {
                if(!((task.getStartTime().isBefore(zooKeeperTask.getStartTime()) && endTime.isBefore(eTime)) || task.getStartTime().isAfter(zooKeeperTask.getStartTime()) && endTime.isAfter(eTime)) || (task.getStartTime().isAfter(zooKeeperTask.getStartTime()) && task.getStartTime().isBefore(eTime)) || (endTime.isAfter(zooKeeperTask.getStartTime()) && endTime.isBefore(eTime))) {
                    throw new SchemaManagementException("Opiekun jest zajęty w tym czasie");
                }
            }
        }
    }

    public List<String> listOfAvailableZooKeepers(Task task) {
        boolean isFree;
        List<String> availableZooKeepers = new ArrayList<>();
        for(ZooKeeper zooKeeper: zooKeeperRepository.findAll()) {
            isFree = true;
            try {
                chekIfZooKeeperIsAvailable(zooKeeper.getLogin(), task);
            } catch (Exception ex) {
                isFree = false;
            }
            if(isFree && userRepository.findByLogin(zooKeeper.getLogin()).getRole().equals("USER")) availableZooKeepers.add(zooKeeper.getLogin());
        }
        return availableZooKeepers;
    }

    public String listWithoutBrackets(List<String> list) {
        return list.toString().substring(1,list.toString().length()-1);
    }

    //GET methods

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(int id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getTaskByLogin(String login) {
        try {
            return  taskRepository.findByLogin(login);
        } catch (Exception ex) {
            //returns an empty list
            return List.of();
        }
    }

    //UPDATE methods + validation

    public Pair<Boolean,String> updateTask(Task task) {
        try{
            taskValidation(task);
            Task existingTask = taskRepository.findById(task.getId()).orElse(null);
            assert existingTask != null;
            existingTask.setDescription(task.getDescription());
            existingTask.setCage(task.getCage());
            existingTask.setLogin(task.getLogin());
            existingTask.setStartTime(task.getStartTime());
            existingTask.setDuration(task.getDuration());
            taskRepository.save(existingTask);
            return Pair.of(true,"");
        } catch (Exception ex) {
            return Pair.of(false,ex.getMessage());

        }
    }

    //DELETE methods + validation

    public Pair<Boolean,String> deleteTask(int id) {
        try {
            taskRepository.deleteById(id);
            return Pair.of(true,"");
        } catch (Exception ex) {
            return Pair.of(false,"Zadanie o takim ID nie istanieje.");
        }
    }
}
