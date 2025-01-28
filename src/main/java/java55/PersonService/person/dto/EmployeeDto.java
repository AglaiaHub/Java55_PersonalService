package java55.PersonService.person.dto;

import java55.PersonService.person.model.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class EmployeeDto extends PersonDto {
    String company;
    int salary;

    public EmployeeDto(Integer id, String name, LocalDate birthDate, AddressDto address, String company, int salary) {
        super(id, name, birthDate, address);
        this.company = company;
        this.salary = salary;
    }
}