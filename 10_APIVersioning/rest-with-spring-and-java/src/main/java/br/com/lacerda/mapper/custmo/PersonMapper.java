package br.com.lacerda.mapper.custmo;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.lacerda.data.vo.v2.PersonVOV2;
import br.com.lacerda.model.Person;

@Service
public class PersonMapper {
	
	public PersonVOV2 convertEntityToVo(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		vo.setAddres(person.getAddres());
		vo.setBirthdate(new Date());
		
		return vo;
	}
	
	public Person convertVoToEntity(PersonVOV2 person) {
		Person entity = new Person();
		
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		entity.setAddres(person.getAddres());
		
		return entity;
	}
}
