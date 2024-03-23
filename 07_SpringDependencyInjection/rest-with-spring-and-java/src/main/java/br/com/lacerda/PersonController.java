package br.com.lacerda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lacerda.model.Person;
import br.com.lacerda.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServices service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable(value="id") Long id) throws Exception{
		
		return service.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll() {
		return service.findAll();
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Person create(@RequestBody Person person) throws Exception{
		
		return service.create(person);
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public Person update(@RequestBody Person person) throws Exception{
		
		return service.update(person);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") Long id) throws Exception{
		
		service.delete(id);
	}
}
