package guru.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="pets")
public class Pet extends BaseEntity {

    @Column(name="name")
    public String name;

    @ManyToOne
    @Column(name="type_id")
    private PetType petType;

    @ManyToOne  // Many pets can have one owner.
    @JoinColumn(name="owner_id")  //tells the JPA how the object will be mapped on DB level. We are expecting to have OWNER ID property on the pet record.
    private Owner owner;

    @Column(name="birth_date")
    private LocalDate birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
