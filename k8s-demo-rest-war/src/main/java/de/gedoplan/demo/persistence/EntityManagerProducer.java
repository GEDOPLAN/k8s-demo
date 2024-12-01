package de.gedoplan.demo.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer {

  @PersistenceContext(unitName = "showcase")
  @Produces
  EntityManager entityManager;
}
