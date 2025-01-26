package java55.PersonService.person.dao;

import java55.PersonService.person.model.Person;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByAddressCity(String city);

    @Query("SELECT p FROM Person p WHERE YEAR(CURRENT_DATE) - YEAR(p.birthDate) BETWEEN :from AND :to")
    List<Person> findByAgeBetween(@Param("from") int from, @Param("to") int to);

    List <Person> findByNameIgnoreCase(String name);
}
