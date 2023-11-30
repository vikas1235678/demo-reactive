package com.example.demoreactive.service;

import com.example.demoreactive.dto.PersonDto;
import com.example.demoreactive.entity.PersonEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

@ExtendWith(SpringExtension.class)
class PersonServiceTest {
    @Mock
    private PersonService personService;


    @Test
    void shouldGetPerson(){
        Mono<PersonDto> personEntityMono = personService.getPersonById(22);
        Predicate<PersonDto> personDtoPredicate = new Predicate<PersonDto>() {
            @Override
            public boolean test(PersonDto person) {
                return person != null;
            }
        };

        StepVerifier
                .create(personEntityMono)
                .expectNextMatches(personDtoPredicate)
                .verifyComplete();
    }
}
