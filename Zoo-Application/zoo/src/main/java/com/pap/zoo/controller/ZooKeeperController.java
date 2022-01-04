package com.pap.zoo.controller;

import com.pap.zoo.entity.ZooKeeper;
import com.pap.zoo.service.ZooKeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/zookeepers")
@CrossOrigin
public class ZooKeeperController {

    @Autowired
    private ZooKeeperService zooKeeperService;

    @PostMapping("/add")
    public Pair<Boolean, String> addZooKeeper(@RequestBody ZooKeeper zooKeeper) { return zooKeeperService.saveZooKeeper(zooKeeper); }

    @GetMapping("/list")
    public List<Pair<ZooKeeper,String>> findAllZooKeepers() { return zooKeeperService.getZooKeepers(); }

    @GetMapping("/zooKeeperById/{id}")
    public ZooKeeper findZooKeeperById(@PathVariable int id) { return zooKeeperService.getZooKeeperById(id); }

    @GetMapping("/listOfLogins")
    public List<String> findZooKeepersLogins() { return zooKeeperService.getZooKeepersLogins(); }

    @PutMapping("/update")//check
    public Pair<Boolean, String> updateZooKeeper(@RequestBody ZooKeeper zooKeeper) { return zooKeeperService.updateZooKeeper(zooKeeper); }

    @DeleteMapping("/delete/{id}")
    public Pair<Boolean, String> deleteZooKeeper(@PathVariable int id) { return zooKeeperService.deleteZooKeeper(id); }
}

