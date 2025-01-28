package java55.PersonService.person.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Embeddable
public class Address implements Serializable {
    String city;
    String street;
    int building;
}
