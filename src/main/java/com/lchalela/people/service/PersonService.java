package com.lchalela.people.service;

import com.lchalela.people.model.Person;

import java.util.List;

public interface PersonService {
    Person getPersonById(Long id);
    List<Person> getAllPerson();
    void savePerson(Person person);
    void updatePerson(Long id, Person person);
    void deletePersonById(Long id);
}