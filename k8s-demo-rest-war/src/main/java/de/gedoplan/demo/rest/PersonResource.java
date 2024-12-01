package de.gedoplan.demo.rest;

import de.gedoplan.demo.entity.Person;
import de.gedoplan.demo.persistence.PersonRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path(PersonResource.PATH)
public class PersonResource {
  public static final String PATH = "person";
  public static final String ID_NAME = "id";
  public static final String ID_TEMPLATE = "{" + ID_NAME + "}";

  @Inject
  PersonRepository personRepository;

  @GET
  @Produces(MediaType.APPLICATION_XML)
  public List<Person> getAll() {
    return this.personRepository.findAll();
  }

  @GET
  @Path(ID_TEMPLATE)
  @Produces(MediaType.APPLICATION_XML)
  public Person getById(@PathParam(ID_NAME) Integer id) {
    Person person = this.personRepository.findById(id);
    if (person != null) {
      return person;
    }

    throw new NotFoundException();
  }

  @PUT
  @Path(ID_TEMPLATE)
  @Consumes(MediaType.APPLICATION_XML)
  public void updatePerson(@PathParam(ID_NAME) Integer id, Person Person) {
    if (!id.equals(Person.getId())) {
      throw new BadRequestException("id of updated object must be unchanged");
    }

    this.personRepository.merge(Person);
  }

  @POST
  @Consumes(MediaType.APPLICATION_XML)
  public Response createPerson(Person Person, @Context UriInfo uriInfo) throws URISyntaxException {
    if (Person.getId() != null) {
      throw new BadRequestException("id of new entry must not be set");
    }

    this.personRepository.persist(Person);

    URI createdUri = uriInfo.getAbsolutePathBuilder().path(PATH).path(ID_TEMPLATE).resolveTemplate(ID_NAME, Person.getId()).build();
    return Response.created(createdUri).build();
  }

  @DELETE
  @Path(ID_TEMPLATE)
  public void deletePerson(@PathParam(ID_NAME) Integer id) {
    this.personRepository.removeById(id);
  }
}
