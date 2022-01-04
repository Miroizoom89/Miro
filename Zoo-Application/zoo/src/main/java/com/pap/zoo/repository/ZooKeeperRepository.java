package com.pap.zoo.repository;

import com.pap.zoo.entity.ZooKeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZooKeeperRepository extends JpaRepository<ZooKeeper, Integer> {

    ZooKeeper findByLogin(String login);
}
