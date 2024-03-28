package br.com.lacerda.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dozermapper.core.Mapper;

import br.com.lacerda.data.vo.v1.PersonVO;
import br.com.lacerda.data.vo.v2.PersonVOV2;
import br.com.lacerda.exceptions.ResourceNotFoundException;
import br.com.lacerda.mapper.DozerMapper;
import br.com.lacerda.model.Person;
import br.com.lacerda.repositories.PersonRepository;
import br.com.lacerda.mapper.custmo.PersonMapper;

@Service
public class PersonServices {

	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	private PersonMapper mapper;
	
	public List<PersonVO> findAll() {

		logger.info("Find all persons");

		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}
	
	public PersonVO findById(Long id) {
		logger.info("Finding one person");
		
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}

	public PersonVOV2 createV2(PersonVOV2 person) {

		logger.info("creating a person with v2");

		Person entity = mapper.convertVoToEntity(person);
		
		return mapper.convertEntityToVo(repository.save(entity));
	}

	public PersonVO create(PersonVO person) {

		logger.info("Create a person");
		// converte de VO para entidade
		Person entity = DozerMapper.parseObject(person, Person.class);
		// cria a entidade no banco
		repository.save(entity);
		// converte de entidade para VO
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO createVO(PersonVO person) {

		logger.info("Create a person");
		// converte de VO para entidade
		Person entity = DozerMapper.parseObject(person, Person.class);
		// cria a entidade no banco
		repository.save(entity);
		// converte de entidade para VO
		return DozerMapper.parseObject(entity, PersonVO.class);
	}

	public PersonVO update(PersonVO person) {

		logger.info("Update a person");
		
		Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records for this Id"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddres(person.getAddres());
		entity.setGender(person.getGender());
		
		repository.save(entity);
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public void delete(Long id) {
		
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records for this Id"));

		logger.info("Delete a person");
		
		repository.delete(entity);	
	}
}
