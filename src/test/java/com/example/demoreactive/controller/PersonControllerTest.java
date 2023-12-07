package com.example.demoreactive.controller;

import com.example.demoreactive.entity.PersonEntity;
import com.example.demoreactive.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebFluxTest(PersonController.class)
class PersonControllerTest {
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @MockBean
    PersonService personService;

    @Autowired
    WebTestClient webTestClient;

    @Test
    void all(){
        Flux<PersonEntity> personDtoFlux = Flux.just(createPersonEntity(14, "Cory", 22),
                createPersonEntity(15, "Smith", 26),
                createPersonEntity(16, "Bob", 25));
        when(personService.all()).thenReturn(personDtoFlux);
        Assertions.assertNotNull(personService.all());
        webTestClient.get()
                .uri("/persons/all")
                .exchange()
                .expectStatus()
                .isOk();
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
        assertNotNull(personService.getPersonById(testPersonId));

        webTestClient.get()
                .uri("/persons/14")
                .exchange()
                .expectStatus()
                .isOk();
    }
    @Test
    void createPerson(){
        PersonEntity personEntity = new PersonEntity(25, "Adam", 26);
        Mono<PersonEntity> personEntityMono = Mono.just(personEntity);
        when(personService.createPerson(any())).thenReturn(personEntityMono);
        Assertions.assertNotNull(personService.createPerson(personEntity));
        webTestClient.post()
                .uri("/persons/person")
                .body(BodyInserters.fromObject(personEntity))
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void updatePerson(){
        int testPersonId = 14;
        PersonEntity personEntity = new PersonEntity(testPersonId, "Alice", 20);
        Mono<PersonEntity> testMonoResponse = Mono.just(personEntity);
        when(personService.updatePerson(any(PersonEntity.class))).thenReturn(testMonoResponse);
        assertNotNull(personService.updatePerson(personEntity));

        webTestClient.put()
                .uri("/persons/person")
                .body(BodyInserters.fromObject(personEntity))
                .exchange()
                .expectStatus()
                .isOk();
    }
    @Test
    void deleteUser(){
        int testPersonId = 14;
        Mono<Void> deleteUserResponse = personService.deletePerson(testPersonId);
        Assertions.assertNull(deleteUserResponse);

        webTestClient.delete()
                .uri("/persons/14")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
