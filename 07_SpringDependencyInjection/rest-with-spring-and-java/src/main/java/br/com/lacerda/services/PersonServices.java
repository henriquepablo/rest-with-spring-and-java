package br.com.lacerda.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.lacerda.model.Person;

@Service
public class PersonServices {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	public List<Person> findAll() {

		logger.info("Find all persons");

		List<Person> persons = new ArrayList<>();

		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}

		return persons;
	}

	public Person findById(String id) {

		logger.info("Finding one person");
		Person person = new Person();

		person.setId(counter.incrementAndGet());
		person.setFirstName("Pablo");
		person.setLastName("Lacerda");
		person.setAddres("Parelheiros - São Paulo - Brasil");
		person.setGender("Male");
		return person;
	}

	public Person create(Person person) {

		logger.info("Create a person");

		return person;
	}

	public Person update(Person person) {

		logger.info("Update a person");

		return person;
	}
	
	public void delete(String id) {
		
		logger.info("Delete a person");
		
	}

	private Person mockPerson(int i) {

		Person person = new Person();

		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("Last name " + i);
		person.setAddres("Some addres in Brasil " + i);
		person.setGender("Male");

		return person;
	}
}
