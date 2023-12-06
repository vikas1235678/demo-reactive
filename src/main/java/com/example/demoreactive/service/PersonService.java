package com.example.demoreactive.service;

import com.example.demoreactive.dto.PersonDto;
import com.example.demoreactive.entity.PersonEntity;
import com.example.demoreactive.repository.PersonRepository;
import com.example.demoreactive.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class PersonService {
    PersonRepository personRepository;

    @Autowired
    PersonService(PersonRepository personRepository){
        this.personRepository=personRepository;
    }

    public Flux<PersonDto> all() {
        Flux<PersonEntity> all = personRepository.findAll();
        return all.map(EntityDtoUtil::toDto);
    }

    public Mono<PersonDto> getPersonById(int id) {
        Mono<PersonEntity> byId = personRepository.findById(id);
        return byId.map(EntityDtoUtil::toDto);

    }

    public Mono<PersonDto> createPerson( Mono<PersonDto> personDtoMono) {
        Mono<PersonEntity> map = personDtoMono.map(EntityDtoUtil::toEntity);
        PersonEntity personEntity = map.block();
        assert personEntity != null;
        Mono<PersonEntity> save = personRepository.save(personEntity);
        return save.map(EntityDtoUtil::toDto);
    }

    public Mono<PersonDto> updatePerson(int id, Mono<PersonDto> personDtoMono) {
        return this.personRepository
                .findById(id)
                .flatMap(u -> personDtoMono
                        .map(EntityDtoUtil::toEntity))
                .doOnNext(e -> e.setId(id))
                .flatMap(this.personRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deletePerson(int id) {
        return this.personRepository.deleteById(id);
    }
}
