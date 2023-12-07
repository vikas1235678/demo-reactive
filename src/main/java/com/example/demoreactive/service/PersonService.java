package com.example.demoreactive.service;

import com.example.demoreactive.entity.PersonEntity;
import com.example.demoreactive.repository.PersonRepository;
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

    public Flux<PersonEntity> all() {
        return personRepository.findAll();
    }

    public Mono<PersonEntity> getPersonById(int id) {
        return personRepository.findById(id);
    }

    public Mono<PersonEntity> createPerson(PersonEntity personEntity) {
        return personRepository.save(personEntity);
    }

    public Mono<PersonEntity> updatePerson(PersonEntity personEntity) {
        Integer personId = personEntity.getId();
        if (personId == null) throw new IllegalArgumentException();
        return personRepository.existsById(personId)
                .flatMap(isExisting -> {
                    if (Boolean.TRUE.equals(isExisting)) {
                        return personRepository.save(personEntity);
                    } else {
                        return Mono.error(new IllegalArgumentException("The person id must exist " +
                                "to update"));
                    }
                });

    }

    public Mono<Void> deletePerson(int id) {
        return this.personRepository.deleteById(id);
    }
}
