package org.virtu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.virtu.domain.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findAll();

//    Client findOneById(Integer id);

//    Client delete(Integer id);

}
