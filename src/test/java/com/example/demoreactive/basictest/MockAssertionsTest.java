package com.example.demoreactive.basictest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MockAssertionsTest {
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    List<String>  list;

    @Mock
    Calculator calculator;


    @Test
    void calculatorTest(){
        when(calculator.add(2,3)).thenReturn(5);
        assertEquals(5,calculator.add(2,3));
    }

    @Test
    void funTest(){
        assertTrue(true);
    }

    @Test
    void mockTest(){
        when(list.size()).thenReturn(10);
        assertEquals(10, list.size());
    }
}
