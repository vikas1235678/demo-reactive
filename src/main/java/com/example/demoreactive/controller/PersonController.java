package com.example.demoreactive.controller;

import com.example.demoreactive.dto.PersonDto;
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
    public Flux<PersonDto> all(){
        return personService.all();
    }

    /**
     * person having the id or 404 not found in case not present
     * @param id person id
     * @return person with provided id
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PersonDto>> personById(@PathVariable int id){
        return this.personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * creates a person in database
     * @param personDtoMono  person name
     * @return newly created person if success else return bad request HTTP error
     */
    @PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PersonDto> createPerson(@RequestBody Mono<PersonDto> personDtoMono){
        return this.personService.createPerson(personDtoMono);
    }

    /**
     * update particular person
     * @param id person id
     * @param personDtoMono updated detail of person
     * @return updated person
     */
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PersonDto>> updatePerson(@PathVariable int id, @RequestBody Mono<PersonDto> personDtoMono){
        return this.personService.updateUser(id, personDtoMono)
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
        return this.personService.deleteUser(id);
    }
}
