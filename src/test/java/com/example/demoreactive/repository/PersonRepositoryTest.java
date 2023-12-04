package com.example.demoreactive.repository;

import com.example.demoreactive.entity.PersonEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

import static org.mockito.Mockito.when;

/**
 * @DataR2dbcTest: Annotation that can be used for a R2DBC test that focuses only on data R2DBC components.
 *                  Using this annotation will disable full autoconfiguration and instead apply configuration
 *                    relevant to data R2DBC tests.
 * @ExtendsWith: is a repeatable annotation that is used to register extensions for the annotated test class,
 *                 test interface, test method, parameter, or field.
 * SpringExtension: It integrates the Spring TestContext Framework into Junit 5's Jupiter Programming model.
 */
@DataR2dbcTest
@ExtendWith(SpringExtension.class)
class PersonRepositoryTest {
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @MockBean
    private PersonRepository personRepository;
    @Test
    void testSaveAPerson(){
        int testPersonId=10;
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName("Joel");
        personEntity.setAge(16);
        personEntity.setId(testPersonId);
        Mono<PersonEntity> personEntityMono = Mono.just(personEntity);
        when(personRepository.save(personEntity)).thenReturn(personEntityMono);
        Assertions.assertEquals(personEntityMono, personRepository.save(personEntity));
    }
}
