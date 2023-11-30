package com.example.demoreactive.repository;

import com.example.demoreactive.entity.PersonEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

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
    @Autowired
    private PersonRepository personRepository;

    @Test
    void shouldSaveSinglePerson(){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName("Joel");
        personEntity.setAge(16);

        Mono<PersonEntity> personEntityMono = personRepository.save(personEntity);

        Predicate<PersonEntity> personEntityPredicate = new Predicate<PersonEntity>() {
            @Override
            public boolean test(PersonEntity person) {
                return person!=null;
            }
        };

        StepVerifier
                .create(personEntityMono)
                .expectNextMatches(personEntityPredicate)
                .verifyComplete();
    }

    @Test
    void shouldCheckIfPersonPresent(){

        Mono<PersonEntity> personEntityMono = personRepository.findById(22);
        Predicate<PersonEntity> personEntityPredicate = new Predicate<PersonEntity>() {
            @Override
            public boolean test(PersonEntity person) {
                return person != null;
            }
        };

        StepVerifier
                .create(personEntityMono)
                .expectNextMatches(personEntityPredicate)
                .verifyComplete();
    }
}
