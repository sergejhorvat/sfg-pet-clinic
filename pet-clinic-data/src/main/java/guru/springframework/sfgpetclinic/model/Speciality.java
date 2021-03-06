package guru.springframework.sfgpetclinic.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

// Specialty class for Vet

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="speciality")
public class Speciality extends BaseEntity {

    // ID will be inherited from the BaseEntity class

    @Column(name="description")
    private String description;

}
