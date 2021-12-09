package com.lchalela.people.service.impl;

import com.lchalela.people.dto.PersonDto;
import com.lchalela.people.exception.NotFoundException;
import com.lchalela.people.mapper.PersonMapper;
import com.lchalela.people.model.Person;
import com.lchalela.people.repository.PersonRepository;
import com.lchalela.people.service.PersonService;
import com.lchalela.people.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public PersonDto getPersonById(Long id) {
        return personMapper.toDto(personRepository.findById(id).orElseThrow(
                ()-> new NotFoundException(messageUtil.getMessage("notFound",null, Locale.getDefault()))
        ));
    }

    @Override
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @Transactional
    @Override
    public void savePerson(PersonDto personDto) {
        Person person = personMapper.toEntity(personDto);
        personRepository.save(person);
    }

    @Transactional
    @Override
    public void updatePerson(Long id, PersonDto personDto) {
        Person person1 = personRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("notFound",null,Locale.getDefault()))
        );
        personMapper.updateEntity(personDto,person1);
        personRepository.save(person1);
    }

    @Override
    public void deletePersonById(Long id) {
        Person person1 = personRepository.findById(id).orElseThrow(
                () -> new NotFoundException(messageUtil.getMessage("notFound",null,Locale.getDefault()))
        );
        personRepository.deleteById(id);
    }
}