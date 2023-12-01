package com.example.demoreactive.repository;



import com.example.demoreactive.entity.PersonEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends R2dbcRepository<PersonEntity, Integer> {

}
