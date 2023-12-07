package com.example.demoreactive.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class PersonEntityTest {

    @MockBean
    PersonEntity personEntity;

    /**
     * you stub either of: final/private/equals()/hashCode() methods.
     *    Those methods *cannot* be stubbed/verified.
     */

    @Test
    void setAsNew(){
        PersonEntity personEntity1 = new PersonEntity();
        when(personEntity.setAsNew()).thenReturn(personEntity1);
        Assertions.assertNotNull(personEntity.setAsNew());
    }

    @Test
    void isNew(){
        when(personEntity.isNew()).thenReturn(true);
        Assertions.assertTrue(personEntity.isNew());
    }

}
