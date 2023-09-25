package global.govstack.mocksris.repositories;

import global.govstack.mocksris.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {}
