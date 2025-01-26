package java55.PersonService.person.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PersonDto {

    Integer id;
    String name;
    LocalDate birthDate;
    AddressDto address;

}


// "id": 1000,
//    "name": "Peter",
//    "birthDate": "1996-11-12",
//    "address": {
//        "city": "Lod",
//        "street": "Bar Kohva",
//        "building": 12
//    }