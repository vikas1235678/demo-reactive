package com.example.demoreactive.controller;

import com.example.demoreactive.dto.PersonDto;
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
        Flux<PersonDto> personDtoFlux = Flux.just(createPersonDto(14, "Cory", 22),
                createPersonDto(15, "Smith", 26),
                createPersonDto(16, "Bob", 25));
        when(personService.all()).thenReturn(personDtoFlux);
        Assertions.assertNotNull(personService.all());
        webTestClient.get()
                .uri("/persons/all")
                .exchange()
                .expectStatus()
                .isOk();

    }

    private PersonDto createPersonDto(int testPersonId, String testPersonName, int testPersonAge){
        return new PersonDto(testPersonId, testPersonName, testPersonAge);
    }
    @Test
    void personById() {
        int testPersonId = 14;
        PersonDto personDto = new PersonDto(testPersonId, "Cory", 20);
        Mono<PersonDto> testMonoResponse = Mono.just(personDto);
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
        PersonDto personDto = new PersonDto(25, "Adam", 26);
        Mono<PersonDto> personDtoMono = Mono.just(personDto);
        when(personService.createPerson(any())).thenReturn(personDtoMono);
        Assertions.assertNotNull(personService.createPerson(personDtoMono));
        webTestClient.post()
                .uri("/persons/person")
                .body(BodyInserters.fromObject(personDto))
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void updatePerson(){
        int testPersonId = 14;
        PersonDto personDto = new PersonDto(testPersonId, "Alice", 20);
        Mono<PersonDto> testMonoResponse = Mono.just(personDto);
        when(personService.updatePerson(anyInt(), any())).thenReturn(testMonoResponse);
        assertNotNull(personService.updatePerson(testPersonId, testMonoResponse));

        webTestClient.put()
                .uri("/persons/14")
                .body(BodyInserters.fromObject(personDto))
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
