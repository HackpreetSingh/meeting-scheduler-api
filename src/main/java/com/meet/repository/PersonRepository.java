package com.meet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meet.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	@Query("Select p from Person p where p.personId in ?1")
	List<Person> findAllByPersonIds(List<Integer> personIds);

	@Query("Select p from Person p where p.personId = ?1")
	List<Person> findByPersonId(Integer personId);

}
