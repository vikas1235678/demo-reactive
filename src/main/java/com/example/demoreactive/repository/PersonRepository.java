package com.example.demoreactive.repository;

import com.example.demoreactive.entity.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends ReactiveCrudRepository<PersonEntity, Integer> {

}
