package com.example.demoreactive.basictest;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * StepVerifier: A StepVerifier provides a declarative way of creating a verifiable script for an async
 *                  Publisher sequence, by expressing expectations about the events that will happen
 *                  upon subscription. The verification must be triggered after the terminal expectations
 *                  (completion, error, cancellation) have been declared, by calling one of the verify()
 *                  methods.
 *      1. Create a stepVerifier around a Publisher using create(Publisher)
 *      2. Setup individual value expectations using expectNext, expectNextMatches(Predicate),
 *          assertNext(Consumer), expectNextCount(long) or expectNextSequence(Iterable).
 *      3. Trigger Subscription actions during the verification using either thenRequest(long) or thenCancel()
 *      4. Trigger the verification of the resulting StepVerifier on its Publisher using either verify() or
 *          verify(Duration)
 *      5. if any expectations failed, an AssertionError will be thrown indicating the failures.
 *
 */
public class BasicTest {
    Calculator c = new Calculator();

    Flux<String> names = Flux.just("n1","n2","n3","n4","n5","n6","n7","n8","n9");

    @Test
    void addition(){
        assertEquals(15, c.add(5, 10));

        StepVerifier
                .create(Flux.just("foo", "bar"))
                .expectNext("foo")
                .expectNext("bar")
                .expectComplete()
                .verify();
        Flux<String> names = Flux.just("n1","n2","n3","n4","n5","n6","n7","n8","n9");
        System.out.println(names.doOnNext(System.out::println));
    }
}
