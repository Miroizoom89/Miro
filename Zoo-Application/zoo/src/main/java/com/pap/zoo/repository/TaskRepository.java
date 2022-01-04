package com.pap.zoo.repository;

import com.pap.zoo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByZooKeeperId(int id);

    List<Task> findBySpeciesId(int id);

    List<Task> findByLogin(String login);
}
