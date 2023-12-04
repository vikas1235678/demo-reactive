package com.example.demoreactive.service;

import com.example.demoreactive.entity.PersonEntity;
import com.example.demoreactive.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
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
    void testFindByIdReturnAPerson(){
        int testPersonId = 14;
        String testPersonName = "Cory";
        int testPersonAge = 20;
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(testPersonId);
        personEntity.setName(testPersonName);
        personEntity.setAge(testPersonAge);
        Mono<PersonEntity> testMonoResponse = Mono.just(personEntity);
        when(personRepository.findById(testPersonId)).thenReturn(testMonoResponse);
        assertNotNull(personRepository.findById(testPersonId));
        Predicate<PersonEntity> personEntityPredicate = new Predicate<PersonEntity>() {
            @Override
            public boolean test(PersonEntity person) {
                return person != null;
            }
        };

        StepVerifier
                .create(personRepository.findById(testPersonId))
                .expectNextMatches(personEntityPredicate)
                .verifyComplete();

    }

    @Test
    void testFindByIdPersonNotFound(){
        int testPersonId = 8;
        when(personRepository.findById(testPersonId)).thenThrow(NullPointerException.class);
        Exception exception = assertThrows(NullPointerException.class, () -> personRepository.findById(testPersonId));
        String expectedMessage = "NullPointerException";
        String actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));
    }


}
