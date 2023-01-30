package com.elibrary.services;

import com.elibrary.models.Person;
import com.elibrary.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);

        if (foundPerson.isPresent()) {
            Hibernate.initialize(foundPerson.get().getBooks());

            foundPerson.get().getBooks().forEach(book -> {
                long diffMill = Math.abs(new Date().getTime() - book.getBookedAt().getTime());

                long tenDaysMillies = 10 * 24 * 60 * 60 * 1000;

                if (diffMill > tenDaysMillies) {
                    book.setOutOfDate(true);
                }
            });
        }
        return foundPerson.orElse(null);
    }

    public Optional<Person> findByName(String name) {
        Optional<Person> foundPerson = peopleRepository.findByFullName(name).stream().findAny();
        return foundPerson;
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatesPerson) {
        updatesPerson.setId(id);
        peopleRepository.save(updatesPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
