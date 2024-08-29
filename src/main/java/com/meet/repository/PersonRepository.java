package com.meet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meet.entity.Person;

/**
 * Repository interface for managing {@link Person} entities.
 * Extends {@link JpaRepository} to provide basic CRUD operations.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    /**
     * Retrieves a list of {@link Person} entities based on a list of person IDs.
     *
     * @param personIds List of IDs of the persons to be retrieved.
     * @return List of {@link Person} entities with IDs matching the provided list.
     */
    @Query("Select p from Person p where p.personId in ?1")
    List<Person> findAllByPersonIds(List<Integer> personIds);

    /**
     * Retrieves a list of {@link Person} entities based on a specific person ID.
     *
     * @param personId ID of the person to be retrieved.
     * @return List of {@link Person} entities with the specified person ID.
     */
    @Query("Select p from Person p where p.personId = ?1")
    List<Person> findByPersonId(Integer personId);

}
