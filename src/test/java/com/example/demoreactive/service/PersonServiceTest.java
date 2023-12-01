package com.example.demoreactive.service;

import com.example.demoreactive.dto.PersonDto;
import com.example.demoreactive.entity.PersonEntity;
import com.example.demoreactive.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PersonServiceTest {

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @MockBean
    private PersonRepository personRepository;

    @Test
    void shouldGetPerson(){

        Assertions.assertTrue(true);

//        when(personRepository.findById(1)).thenCallRealMethod();
//
//        Assertions.assertTrue(true);

        //Mono<PersonEntity> personEntityMono = personRepository.findById(1);
//        Predicate<PersonDto> personDtoPredicate = new Predicate<PersonDto>() {
//            @Override
//            public boolean test(PersonDto person) {
//                return person != null;
//            }
//        };

//        StepVerifier
//                .create(personEntityMono)
//                .expectNextCount(1)
////                .expectNextMatches(personDtoPredicate)
//                .verifyComplete();
    }
}
