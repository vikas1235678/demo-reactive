package com.example.demoreactive.controller;

import com.example.demoreactive.dto.PersonDto;
import com.example.demoreactive.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(PersonController.class)
class PersonControllerTest {
    private final Integer testPersonId = 1;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @MockBean
    private PersonService personService;

    @Test
    void testFindByIdReturnAPerson() {
        int testPersonId = 14;
        String testPersonName = "Cory";
        int testPersonAge = 20;
        PersonDto personDto = new PersonDto();
        personDto.setId(testPersonId);
        personDto.setName(testPersonName);
        personDto.setAge(testPersonAge);
        Mono<PersonDto> testMonoResponse = Mono.just(personDto);
        when(personService.getPersonById(testPersonId)).thenReturn(testMonoResponse);
        assertNotNull(personService.getPersonById(testPersonId));
    }
}
