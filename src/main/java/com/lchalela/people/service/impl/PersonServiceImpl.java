package com.lchalela.people.service.impl;

import com.lchalela.people.exception.NotFoundException;
import com.lchalela.people.model.Person;
import com.lchalela.people.repository.PersonRepository;
import com.lchalela.people.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;


    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Person not found")
        );
    }

    @Override
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public void savePerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public void updatePerson(Long id, Person person) {
        Person person1 = personRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Person not found")
        );
        person1.setName( person.getName());
        person1.setLastName( person.getLastName());
        personRepository.save(person1);
    }

    @Override
    public void deletePersonById(Long id) {
        Person person1 = personRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Person not found")
        );
        personRepository.deleteById(id);
    }
}