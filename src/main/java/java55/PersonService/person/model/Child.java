package java55.PersonService.person.model;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Child extends Person {
    String kindergarden;

    public Child(Integer id, String name, LocalDate birthDate, Address address, String kindergarden) {
        super(id, name, birthDate, address);
        this.kindergarden = kindergarden;
    }
}
