package global.govstack.mocksris.service;

import global.govstack.mocksris.controller.dto.CreatePersonDto;
import global.govstack.mocksris.model.Person;
import global.govstack.mocksris.repositories.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Person save(CreatePersonDto personDto) {
    Person person = new Person(personDto);
    return personRepository.save(person);
  }

  public Person save(Person person) {
    return personRepository.save(person);
  }
}
