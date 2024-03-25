package br.com.lacerda.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lacerda.data.vo.v1.PersonVO;
import br.com.lacerda.exceptions.ResourceNotFoundException;
import br.com.lacerda.repositories.PersonRepository;

@Service
public class PersonServices {

	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<PersonVO> findAll() {

		logger.info("Find all persons");

		return repository.findAll();
	}

	public PersonVO findById(Long id) {

		logger.info("Finding one person");

		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records for this Id"));
	}

	public PersonVO create(PersonVO person) {

		logger.info("Create a person");

		return repository.save(person);
	}

	public PersonVO update(PersonVO person) {

		logger.info("Update a person");
		
		PersonVO entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records for this Id"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddres(person.getAddres());
		entity.setGender(person.getGender());
		
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		
		PersonVO entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records for this Id"));

		logger.info("Delete a person");
		
		repository.delete(entity);	
	}
}
