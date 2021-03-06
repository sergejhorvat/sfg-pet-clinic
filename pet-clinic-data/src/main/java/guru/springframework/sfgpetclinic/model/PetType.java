package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="types")
public class PetType extends BaseEntity{

    // Set up construcotr four builder() so it can use id property and name
    @Builder
    public PetType(Long id, String name){
        super(id);
        this.name = name;
    }

    @Column(name="name")
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
