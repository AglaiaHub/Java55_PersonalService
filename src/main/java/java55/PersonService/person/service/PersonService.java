package java55.PersonService.person.service;

import java55.PersonService.person.dto.AddressDto;
import java55.PersonService.person.dto.CityPopulationDto;
import java55.PersonService.person.dto.EmployeeDto;
import java55.PersonService.person.dto.PersonDto;

import java.util.Collection;

public interface PersonService {
    boolean addPerson(PersonDto personDto);

    PersonDto findPersonById(Integer id);


    Iterable<PersonDto> findPersonsByCity(String city);

    Iterable<PersonDto> findPersonsByAge(int from, int to);

    PersonDto updateName(Integer id, String newName);

    Iterable<PersonDto> findPersonsByName(String name);

    Iterable<CityPopulationDto> getCitiesPopulation();

    PersonDto updateAddress(Integer id, AddressDto adress);

    PersonDto removePersonById(Integer id);

    Iterable<EmployeeDto> findEmployeeBySalary(int min, int max);
}
