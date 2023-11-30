package com.example.demoreactive.controller;

import com.example.demoreactive.dto.PersonDto;
import com.example.demoreactive.entity.PersonEntity;
import com.example.demoreactive.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(PersonController.class)
class PersonControllerTest {
    private final Integer TestPersonId = 1;
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private PersonService personService;

    @Test
    void shouldGetPerson(){

        PersonDto personDto = new PersonDto();

        when(personService.getPersonById(TestPersonId)).thenReturn(Mono.just(personDto));

        WebTestClient.BodySpec<PersonDto, ?> personDtoBodySpec = webTestClient
                .get().uri("/persons/" + TestPersonId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(PersonDto.class);
        System.out.println(personDtoBodySpec.returnResult());
    }
}
