package java55.PersonService.person.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import java55.PersonService.person.dto.AddressDto;
import java55.PersonService.person.dto.CityPopulationDto;
import java55.PersonService.person.dto.EmployeeDto;
import java55.PersonService.person.dto.PersonDto;
import java55.PersonService.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    final PersonService personService;

    @PostMapping
    public boolean addPerson (@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    @GetMapping ("/{id}")
    public PersonDto findPersonById(@PathVariable Integer id) {
        return personService.findPersonById(id);
    }


    @GetMapping ("/city/{city}")
    public Iterable<PersonDto> findPersonsByCity(@PathVariable String city) {
        return personService.findPersonsByCity(city);
    }

    @GetMapping ("/ages/{from}/{to}")
    public Iterable<PersonDto> findPersonsByAge(@PathVariable int from, @PathVariable int to) {
        return personService.findPersonsByAge(from, to);
    }

    @PutMapping ("/{id}/name/{newName}")
    public PersonDto updateName(@PathVariable Integer id, @PathVariable String newName) {
        return personService.updateName(id, newName);
    }

    @GetMapping ("/name/{name}")
    public Iterable<PersonDto> findPersonsByName(@PathVariable String name) {
        return personService.findPersonsByName(name);
    }

    @GetMapping ("/population/city")
    public Iterable<CityPopulationDto> getCityPopulation() {
//        return personService.getCitiesPopulation();
        return null;
    }

    @PutMapping ("/{id}/address")
    public PersonDto updateAddress (@PathVariable Integer id, @RequestBody AddressDto adress){
        return personService.updateAddress(id, adress);
    }

    @DeleteMapping ("/{id}")
    public PersonDto removePersonById(@PathVariable Integer id) {
        return personService.removePersonById(id);
    }

    //todo
//    @GetMapping("/salary/{min}/{max}")
//    public Iterable<EmployeeDto> findEmployeeBySalary ()

//    @GetMapping("/children")
    //    public Iterable<ChildDto> findAllChildren ()

    // postmanCollection добавить в корень, где pom-файл

    //todo настроить getCityPopulation

    //todo * настроить модель маппер в отдельный компонент, modelMapperConfig - чтобы не приходилось переписывать код с if
    //todo * закрыть новые методы тестами. Только сервис - только бизнеслогику
}
