package com.example.demoreactive.util;

import com.example.demoreactive.dto.PersonDto;
import com.example.demoreactive.entity.PersonEntity;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {
    public static PersonDto toDto(PersonEntity personEntity) {
        PersonDto personDto = new PersonDto();
        BeanUtils.copyProperties(personEntity, personDto);
        return personDto;
    }

    public static PersonEntity toEntity(PersonDto personDto) {
        PersonEntity personEntity = new PersonEntity();
        BeanUtils.copyProperties(personDto, personEntity);
        return personEntity;
    }
}
