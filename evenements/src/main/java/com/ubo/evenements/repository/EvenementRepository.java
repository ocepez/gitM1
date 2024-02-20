package com.ubo.evenements.repository;


import com.ubo.evenements.entity.Evenements;
import org.springframework.data.repository.CrudRepository;

public interface EvenementRepository extends CrudRepository<Evenements, Integer> {

}
