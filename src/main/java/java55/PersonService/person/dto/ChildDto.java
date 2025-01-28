package java55.PersonService.person.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class ChildDto extends PersonDto {
    String kidergarden;

    public ChildDto(Integer id, String name, LocalDate birthDate, AddressDto address, String kidergarden) {
        super(id, name, birthDate, address);
        this.kidergarden = kidergarden;
    }
}
