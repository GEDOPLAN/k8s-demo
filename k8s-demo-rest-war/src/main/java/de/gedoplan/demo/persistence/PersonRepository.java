package de.gedoplan.demo.persistence;

import de.gedoplan.baselibs.persistence.repository.SingleIdEntityRepository;
import de.gedoplan.demo.entity.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(rollbackOn = Exception.class)
public class PersonRepository extends SingleIdEntityRepository<Integer, Person> {
}
