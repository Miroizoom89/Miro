package com.pap.zoo;

import com.pap.zoo.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class ZooApplication {

    @Autowired
    private static SpeciesService speciesService;

    public static void main(String[] args) {
        SpringApplication.run(ZooApplication.class, args);
        speciesService.createFeedingTasks();
    }
}
