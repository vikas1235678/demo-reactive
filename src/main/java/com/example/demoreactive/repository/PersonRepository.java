package com.example.demoreactive.repository;



import com.example.demoreactive.entity.PersonEntity;
import com.example.demoreactive.ui.Person;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends R2dbcRepository<PersonEntity, Integer> {

}
