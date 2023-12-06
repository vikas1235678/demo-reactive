package com.example.demoreactive.service;

import com.example.demoreactive.dto.PersonDto;
import com.example.demoreactive.entity.PersonEntity;
import com.example.demoreactive.repository.PersonRepository;
import com.example.demoreactive.util.EntityDtoUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    PersonRepository personRepository = Mockito.mock(PersonRepository.class);

    @Test
    void all(){
        PersonEntity personEntity1 = createPersonEntity(14, "Cory", 22);
        PersonEntity personEntity2 = createPersonEntity(15, "Smith", 26);
        PersonEntity personEntity3 = createPersonEntity(16, "Bob", 25);
        Flux<PersonEntity> personEntityFlux = Flux.just(personEntity1, personEntity2, personEntity3);
        when(personRepository.findAll()).thenReturn(personEntityFlux);
        Assertions.assertNotNull(personRepository.findAll().map(EntityDtoUtil::toDto));
        PersonDto personDto1 = EntityDtoUtil.toDto(personEntity1);
        PersonDto personDto2 = EntityDtoUtil.toDto(personEntity2);
        PersonDto personDto3 = EntityDtoUtil.toDto(personEntity3);

        StepVerifier.create(personRepository.findAll().map(EntityDtoUtil::toDto))
                .expectNext(personDto1)
                .expectNext(personDto2)
                .expectNext(personDto3)
                .verifyComplete();
    }

    private PersonEntity createPersonEntity(int testPersonId, String testPersonName, int testPersonAge){
        return new PersonEntity(testPersonId, testPersonName, testPersonAge);
    }

    @Test
    void personById() {
        int testPersonId = 14;
        PersonEntity personEntity = new PersonEntity(testPersonId, "Cory", 20);
        Mono<PersonEntity> testMonoResponse = Mono.just(personEntity);
        when(personRepository.findById(anyInt())).thenReturn(testMonoResponse);
        assertNotNull(personRepository.findById(testPersonId).map(EntityDtoUtil::toDto));

        StepVerifier.create(personRepository.findById(testPersonId))
                .expectNext(personEntity)
                .verifyComplete();
    }
    @Test
    void createPerson(){
        PersonEntity personEntity = new PersonEntity(25, "Adam", 26);
        Mono<PersonEntity> personEntityMono = Mono.just(personEntity);
        when(personRepository.save(any())).thenReturn(personEntityMono);
        Assertions.assertNotNull(personRepository.save(personEntity).map(EntityDtoUtil::toDto));

        StepVerifier.create(personRepository.save(personEntity))
                .expectNext(personEntity)
                .verifyComplete();
    }

    @Test
    void updatePerson(){
        int testPersonId = 14;
        PersonEntity personEntity = new PersonEntity(testPersonId, "Alice", 20);
        Mono<PersonEntity> testMonoResponse = Mono.just(personEntity);
        when(personRepository.save(any())).thenReturn(testMonoResponse);
        Assertions.assertNotNull(personRepository.save(personEntity).map(EntityDtoUtil::toDto));

        StepVerifier.create(personRepository.save(personEntity))
                .expectNext(personEntity)
                .verifyComplete();
    }
    @Test
    void deleteUser(){
        int testPersonId = 14;
        StepVerifier.create(personRepository.deleteById(testPersonId))
                .expectComplete();
    }
}
