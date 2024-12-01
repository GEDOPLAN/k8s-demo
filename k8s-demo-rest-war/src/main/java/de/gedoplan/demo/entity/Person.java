package de.gedoplan.demo.entity;

import de.gedoplan.baselibs.persistence.entity.GeneratedIntegerIdEntity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Access(AccessType.FIELD)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Person extends GeneratedIntegerIdEntity {

  private String name;
  private String firstname;

  public Person(String name, String firstname) {
    this.name = name;
    this.firstname = firstname;
  }

}
