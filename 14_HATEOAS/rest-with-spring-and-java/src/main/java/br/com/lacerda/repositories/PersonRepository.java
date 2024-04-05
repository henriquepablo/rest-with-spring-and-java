package br.com.lacerda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lacerda.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
