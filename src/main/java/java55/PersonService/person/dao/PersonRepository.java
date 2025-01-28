package java55.PersonService.person.dao;

import java55.PersonService.person.dto.CityPopulationDto;
import java55.PersonService.person.model.Employee;
import java55.PersonService.person.model.Person;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PersonRepository extends JpaRepository<Person, Integer> {

    Collection<Person> findByAddressCity(String city);

    @Query("SELECT p FROM Person p WHERE YEAR(CURRENT_DATE) - YEAR(p.birthDate) BETWEEN :from AND :to")
    Collection<Person> findByAgeBetween(@Param("from") int from, @Param("to") int to);

    Collection <Person> findByNameIgnoreCase(String name);

    @Query("SELECT p FROM Person p WHERE p.salary BETWEEN :min AND :max")
    Collection <Employee> findBySalaryBetween(@Param("min")int min, @Param("max")int max);

//    select p.city, count(*) from person as p group by p.city;
//    @Query("select new java55.PersonService.person.dto.CityPopulationDto(p.address.city, count(p)) " +
//            "from Person as p group by p.address.city")
//    List<CityPopulationDto> getCityPopulation();

}
