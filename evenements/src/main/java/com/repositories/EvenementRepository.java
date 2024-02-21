package com.repositories;


import com.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EvenementRepository extends JpaRepository<Evenement, Integer> {

}
