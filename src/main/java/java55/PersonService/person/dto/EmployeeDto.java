package java55.PersonService.person.dto;

import lombok.Getter;

@Getter
public class EmployeeDto extends PersonDto {
    String company;
    int salary;
}