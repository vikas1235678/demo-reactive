package com.example.demoreactive.controller;

import com.example.demoreactive.dto.PersonDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PersonControllerTest {
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @MockBean
    private PersonController personController;

    @Test
    void all(){
        Flux<PersonDto> personDtoFlux = Flux.just(createPersonDto(14, "Cory", 22),
                createPersonDto(15, "Smith", 26),
                createPersonDto(16, "Bob", 25));
        when(personController.all()).thenReturn(personDtoFlux);
        Assertions.assertNotNull(personController.all());
    }

    private PersonDto createPersonDto(int testPersonId, String testPersonName, int testPersonAge){
        return new PersonDto(testPersonId, testPersonName, testPersonAge);
    }
    @Test
    void personById() {
        int testPersonId = 14;
        PersonDto personDto = new PersonDto(testPersonId, "Cory", 20);
        Mono<PersonDto> testMonoResponse = Mono.just(personDto);
        Mono<ResponseEntity<PersonDto>> testMonoResEntityResponse =
                testMonoResponse.map(ResponseEntity::ok)
                        .defaultIfEmpty(ResponseEntity.notFound().build());
        when(personController.personById(anyInt())).thenReturn(testMonoResEntityResponse);
        assertNotNull(personController.personById(testPersonId));
    }
    @Test
    void createPerson(){
        PersonDto personDto = new PersonDto(25, "Adam", 26);
        Mono<PersonDto> personDtoMono = Mono.just(personDto);
        when(personController.createPerson(any())).thenReturn(personDtoMono);
        Assertions.assertNotNull(personController.createPerson(personDtoMono));
    }

    @Test
    void updatePerson(){
        int testPersonId = 14;
        PersonDto personDto = new PersonDto(testPersonId, "Alice", 20);
        Mono<PersonDto> testMonoResponse = Mono.just(personDto);
        Mono<ResponseEntity<PersonDto>> testMonoResEntityResponse =
                testMonoResponse.map(ResponseEntity::ok)
                        .defaultIfEmpty(ResponseEntity.notFound().build());
        when(personController.updatePerson(anyInt(), any())).thenReturn(testMonoResEntityResponse);
        assertNotNull(personController.updatePerson(testPersonId, testMonoResponse));
    }
    @Test
    void deleteUser(){
        int testPersonId = 14;
        Mono<Void> deleteUserResponse = personController.deleteUser(testPersonId);
        Assertions.assertNull(deleteUserResponse);
    }
}
