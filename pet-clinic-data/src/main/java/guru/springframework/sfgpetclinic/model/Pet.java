package guru.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    // pet can visit vet multiple times
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")  // pet properties maps back to pet in Visit class // CascadeType.ALL will delete visits associated to pet if pet id deleted
    private Set<Visit> visits = new HashSet<>(); // Set relation to visits and initialize set not to get NULL pointer exception

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

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }
}
