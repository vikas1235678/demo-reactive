package com.example.demoreactive.service;

import com.example.demoreactive.dto.PersonDto;
import com.example.demoreactive.repository.PersonRepository;
import com.example.demoreactive.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public Flux<PersonDto> all() {
        return this.personRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<PersonDto> getPersonById(int id) {
        return this.personRepository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<PersonDto> createPerson( Mono<PersonDto> personDtoMono) {
        return personDtoMono
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.personRepository::save)
                .map(EntityDtoUtil::toDto);
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
