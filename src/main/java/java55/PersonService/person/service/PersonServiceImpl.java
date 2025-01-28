package java55.PersonService.person.service;

import java55.PersonService.person.dao.PersonRepository;
import java55.PersonService.person.dto.AddressDto;
import java55.PersonService.person.dto.CityPopulationDto;
import java55.PersonService.person.dto.EmployeeDto;
import java55.PersonService.person.dto.PersonDto;
import java55.PersonService.person.dto.exception.PersonNotFoundException;
import java55.PersonService.person.model.Address;
import java55.PersonService.person.model.Child;
import java55.PersonService.person.model.Employee;
import java55.PersonService.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {
    final ModelMapper modelMapper;
    final PersonRepository personRepository;

    @Transactional
    @Override
    public boolean addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())){
            return false;
        }
        Person person = modelMapper.map(personDto, Person.class);
        personRepository.save(person);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<PersonDto> findPersonsByCity(String city) {
        return personRepository.findByAddressCity(city)
                .stream()
                .map(p ->
//                        {
//                    if(per)
//                }
                modelMapper.map(p, PersonDto.class))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<PersonDto> findPersonsByAge(int from, int to) {
        return personRepository.findByAgeBetween(from, to)
                .stream()
                .map(p -> modelMapper.map(p, PersonDto.class))
                .toList();
    }

    @Transactional
    @Override
    public PersonDto updateName(Integer id, String newName) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setName(newName);
        person = personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<PersonDto> findPersonsByName(String name) {
        return personRepository.findByNameIgnoreCase(name)
                .stream()
                .map(p -> modelMapper.map(p, PersonDto.class))
                .toList();
    }

    @Transactional
    @Override
    public PersonDto updateAddress(Integer id, AddressDto addressDto) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        Address address = modelMapper.map(addressDto, Address.class);
        person.setAddress(address);
        person = personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Transactional
    @Override
    public PersonDto removePersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        personRepository.delete(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public Iterable<EmployeeDto> findEmployeeBySalary(int min, int max) {
        return personRepository.findBySalaryBetween(min, max)
                .stream()
                .map(p -> modelMapper.map(p, EmployeeDto.class))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<CityPopulationDto> getCitiesPopulation() {
//        return personRepository.getCityPopulation();
        return null;
    }


    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if(personRepository.count() == 0) {
            Person person = new Person(1000, "John", LocalDate.of(1988, 3, 13),
                    new Address("Tel Aviv", "Ben Gurion", 81));
            Child child = new Child(2000, "Moshe", LocalDate.of(2022, 1, 27),
                    new Address("Ashkelon", "Bar Kohva", 21),
                    "Shalom");
            Employee employee = new Employee(3000, "Sarah", LocalDate.of(2000, 11, 23),
                    new Address("Rehovot", "Herzel", 2),
            "Motorola", 25000);
            personRepository.save(person);
            personRepository.save(child);
            personRepository.save(employee);
        }
    }
}
