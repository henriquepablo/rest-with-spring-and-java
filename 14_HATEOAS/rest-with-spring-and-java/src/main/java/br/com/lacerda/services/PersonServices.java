package br.com.lacerda.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.lacerda.controllers.PersonController;
import br.com.lacerda.data.vo.v1.PersonVO;
import br.com.lacerda.exceptions.ResourceNotFoundException;
import br.com.lacerda.mapper.DozerMapper;
import br.com.lacerda.model.Person;
import br.com.lacerda.repositories.PersonRepository;

@Service
public class PersonServices {

	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<PersonVO> findAll() {

		logger.info("Find all persons");

		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		
		persons.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		return persons;
	}

	public PersonVO findById(Long id) throws Exception {

		logger.info("Finding one person");

		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records for this Id"));
		
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}

	public PersonVO create(PersonVO person) throws Exception {
		
		logger.info("Create a person");
		// converte de VO para entidade
		
		Person entity = new Person();
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddres(person.getAddress());
		entity.setGender(person.getGender());
		
		// cria a entidade no banco
		PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		// converte de entidade para VO
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public PersonVO update(PersonVO person) throws Exception {

		logger.info("Update a person");
		
		Person entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records for this Id"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddres(person.getAddress());
		entity.setGender(person.getGender());
		
		PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		// converte de entidade para VO
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records for this Id"));

		logger.info("Delete a person");
		
		repository.delete(entity);	
	}
}
