package com.lchalela.people.service;

import com.lchalela.people.dto.PersonDto;
import com.lchalela.people.model.Person;

import java.util.List;

public interface PersonService {
    PersonDto getPersonById(Long id);
    List<Person> getAllPerson();
    void savePerson(PersonDto personDto);
    void updatePerson(Long id, PersonDto personDto);
    void deletePersonById(Long id);
}