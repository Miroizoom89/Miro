package com.pap.zoo.service;

import com.pap.zoo.entity.ZooKeeper;
import com.pap.zoo.repository.UserRepository;
import com.pap.zoo.repository.ZooKeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ZooKeeperService {

    @Autowired
    private ZooKeeperRepository zooKeeperRepository;

    @Autowired
    private UserRepository userRepository;

    //PUSH methods + validation

    public Pair<Boolean,String> saveZooKeeper(ZooKeeper zooKeeper) {
        try {
            userRepository.findByLogin(zooKeeper.getLogin()).setZooKeeper(zooKeeper);
            zooKeeperRepository.save(zooKeeper);
            return Pair.of(true,"");
        } catch (Exception ex) {
            return Pair.of(false,"Nie można dodać pracownika, nie ma konta o podanym loginie.");
        }
    }

    //GET methods

    public List<Pair<ZooKeeper,String>> getZooKeepers() {
        List<Pair<ZooKeeper,String>> fullZooKeeper = new ArrayList<>();
        try {
            for (ZooKeeper zookeeper: zooKeeperRepository.findAll()) {
                String role;
                role = Objects.requireNonNull(userRepository.findById(zookeeper.getId()).orElse(null)).getRole();
                fullZooKeeper.add(Pair.of(zookeeper,role));
            }
        } catch (Exception ex) {
            return fullZooKeeper;
        }
        return fullZooKeeper;
    }

    public ZooKeeper getZooKeeperById(int id) { return zooKeeperRepository.findById(id).orElse(null); }

    public List<String> getZooKeepersLogins() {
        List<String> loginList = new ArrayList<>();
        for (ZooKeeper zooKeeper: zooKeeperRepository.findAll()) {
            if(userRepository.findByLogin(zooKeeper.getLogin()).getRole().equals("USER")) {
                loginList.add(zooKeeper.getLogin());
            }
        }
        return loginList;
    }

    //UPDATE methods + validation

    public Pair<Boolean,String> updateZooKeeper(ZooKeeper zooKeeper) {
        try {
            ZooKeeper existingZooKeeper = zooKeeperRepository.findById(zooKeeper.getId()).orElse(null);
            assert existingZooKeeper != null;
            existingZooKeeper.setName(zooKeeper.getName());
            existingZooKeeper.setSurname(zooKeeper.getSurname());
            existingZooKeeper.setLogin(zooKeeper.getLogin());
            zooKeeperRepository.save(existingZooKeeper);
            return Pair.of(true,"");
        } catch (Exception ex) {
            return Pair.of(false,"Podano niepoprawny login.");
        }
    }

    //DELETE methods + validation

    public Pair<Boolean,String> deleteZooKeeper(int id) {
        try {
            zooKeeperRepository.deleteById(id);
            return Pair.of(true,"");
        } catch (Exception ex) {
            return Pair.of(false,"Pracownik o takim ID nie został znaleziony.");
        }
    }
}
