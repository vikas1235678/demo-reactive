package com.example.demoreactive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Reactor Test (reactor-test) has two main components StepVerifier and TestPublisher
 * 1. StepVerifier: Provides a declarative way of creating a verifiable script for an async Publisher
 * 					sequence by expressing expectations about the events that will happen upon subscription
 * 2. TestPublisher: Is a Publisher that we can directly manipulate, triggering onNext, onComplete, and onError
 * 						events, for testing purposes.
 *	Junit Jupiter: The JUnit platform is responsible for launching testing framework on the JVM. Junit jupiter
 *					module includes new programming and extension models for writing tests in JUnit 5
 *				(a) Mockito: Mockito is a mocking framework for Junit test cases.
 *				(b) WebTestClient: WebTestClient can be used to test WebFlux server endpoints with or without
 *									running the server.
 */
@SpringBootTest
class DemoReactiveApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void main(){
		DemoReactiveApplication.main(new String[]{});
	}

}
