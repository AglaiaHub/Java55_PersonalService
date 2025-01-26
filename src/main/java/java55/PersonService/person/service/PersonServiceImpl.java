package java55.PersonService.person.service;

import java55.PersonService.person.dao.PersonRepository;
import java55.PersonService.person.dto.AddressDto;
import java55.PersonService.person.dto.PersonDto;
import java55.PersonService.person.dto.exception.PersonNotFoundException;
import java55.PersonService.person.model.Address;
import java55.PersonService.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    final ModelMapper modelMapper;
    final PersonRepository personRepository;
    @Override
    public boolean addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())){
            return false;
        }
        Person person = modelMapper.map(personDto, Person.class);
        personRepository.save(person);
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public Iterable<PersonDto> findPersonsByCity(String city) {
        return personRepository.findByAddressCity(city).stream()
                .map(p -> modelMapper.map(p, PersonDto.class))
                .toList();
//        return null;
    }

    @Override
    public Iterable<PersonDto> findPersonsByAge(int from, int to) {
//        return personRepository.findByAgeBetween(from, to).stream()
//                .map(p -> modelMapper.map(p, PersonDto.class))
//                .toList();
        return null;
    }

    @Override
    public PersonDto updateName(Integer id, String newName) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setName(newName);
        person = personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public Iterable<PersonDto> findPersonsByName(String name) {
        return personRepository.findByNameIgnoreCase(name).stream()
                .map(p -> modelMapper.map(p, PersonDto.class))
                .toList();
//        return null;
    }

    @Override
    public Iterable<PersonDto> getCityPopulation(String city) {
        //TODO
        return null;
    }

    @Override
    public PersonDto updateAddress(Integer id, AddressDto addressDto) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        Address address = modelMapper.map(addressDto, Address.class);
        person.setAddress(address);
        person = personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto removePersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        personRepository.delete(person);
        return modelMapper.map(person, PersonDto.class);
    }
}
