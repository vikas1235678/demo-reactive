package com.example.demoreactive.controller;

import com.example.demoreactive.entity.PersonEntity;
import com.example.demoreactive.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/persons")
public class PersonController {
    PersonService personService;
    @Autowired
    PersonController(PersonService personService){
        this.personService=personService;
    }

    /**
     * all person present in person database
     * @return all person
     */
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<PersonEntity> all(){
        return personService.all();
    }

    /**
     * person having the id or 404 not found in case not present
     * @param id person id
     * @return person with provided id
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PersonEntity>> personById(@PathVariable int id){
        return this.personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * creates a person in database
     * @param personEntity  person object
     * @return newly created person if success else return bad request HTTP error
     */
    @PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PersonEntity> createPerson(@RequestBody PersonEntity personEntity){
        return this.personService.createPerson(personEntity);
    }

    /**
     * update particular person
     * @param personEntity updated detail of person
     * @return updated person
     */
    @PutMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PersonEntity>> updatePerson(@RequestBody PersonEntity personEntity){
        return this.personService.updatePerson(personEntity)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * delete particular person
     * @param id person id
     * @return does not return anything
     */
    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable int id){
        return this.personService.deletePerson(id);
    }
}
