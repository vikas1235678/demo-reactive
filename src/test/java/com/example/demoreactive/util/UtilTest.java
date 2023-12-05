package com.example.demoreactive.util;

import com.example.demoreactive.dto.PersonDto;
import com.example.demoreactive.entity.PersonEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UtilTest {

    static PersonDto personDto;
    static PersonEntity personEntity;
    @BeforeAll
    static void setup(){
        personDto = new PersonDto();
        personDto.setId(1);
        personDto.setName("test");
        personDto.setAge(20);

        personEntity = new PersonEntity();
        personEntity.setId(1);
        personEntity.setName("test");
        personEntity.setAge(20);
    }

    @Test
    void testToDto(){
       PersonDto actualPersonDto = EntityDtoUtil.toDto(personEntity);
       Assertions.assertEquals(personDto, actualPersonDto);
    }

    @Test
    void testToEntity(){
        PersonEntity actualPersonEntity = EntityDtoUtil.toEntity(personDto);
        Assertions.assertEquals(personEntity, actualPersonEntity);
    }
}
