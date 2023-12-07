package com.example.demoreactive.service;

import com.example.demoreactive.entity.PersonEntity;
import com.example.demoreactive.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class PersonServiceTest {
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @MockBean
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    @Test
    void all(){
        PersonEntity personEntity1 = createPersonEntity(14, "Cory", 22);
        PersonEntity personEntity2 = createPersonEntity(15, "Smith", 26);
        PersonEntity personEntity3 = createPersonEntity(16, "Bob", 25);
        Flux<PersonEntity> personEntityFlux = Flux.just(personEntity1, personEntity2, personEntity3);
        when(personService.all()).thenReturn(personEntityFlux);
        Assertions.assertTrue(true);
        personService.all();
        StepVerifier.create(personService.all()).expectNext(personEntity1)
                .expectNext(personEntity2)
                .expectNext(personEntity3)
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
        when(personService.getPersonById(anyInt())).thenReturn(testMonoResponse);
        personService.getPersonById(testPersonId);
        StepVerifier.create(personService.getPersonById(testPersonId))
                .expectNext(personEntity)
                .verifyComplete();
    }
    @Test
    void createPerson(){
        PersonEntity personEntity = new PersonEntity(25, "Adam", 26);
        Mono<PersonEntity> personEntityMono = Mono.just(personEntity);
        when(personService.createPerson(any(PersonEntity.class))).thenReturn(personEntityMono);
        personService.createPerson(personEntity);
        StepVerifier.create(personService.createPerson(personEntity))
                .expectNext(personEntity)
                .verifyComplete();
    }

    @Test
    void updatePerson() {
        int testPersonId = 14;
        PersonEntity personEntity = new PersonEntity(testPersonId, "Alice", 20);
        Mono<PersonEntity> testMonoResponse = Mono.just(personEntity);
        Mono<Boolean> testMonoBoolean = Mono.just(Boolean.TRUE);
        if (personEntity.getId() == null) throw new IllegalArgumentException();
        when(personRepository.existsById(testPersonId)).thenReturn(testMonoBoolean);
        when(personService.updatePerson(personEntity)).thenReturn(testMonoResponse);
        personService.updatePerson(personEntity);
        personRepository.existsById(testPersonId).flatMap(isExisting -> {
            if(Boolean.TRUE.equals(isExisting)){
                personRepository.save(personEntity);
            }
            else {
                Mono.error(new IllegalArgumentException("The person id must exist " +
                        "to update"));
            }
            return testMonoBoolean;
        });
        Assertions.assertTrue(true);
    }
    @Test
    void deleteUser(){
        int testPersonId = 14;
        StepVerifier.create(personService.deletePerson(testPersonId))
                .expectComplete();
        personService.deletePerson(testPersonId);
    }
}
