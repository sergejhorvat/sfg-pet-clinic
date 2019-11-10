package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pets")
public class Pet extends BaseEntity {

    @Builder
    public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;

        if (visits == null || visits.size() > 0){
            this.visits = visits;
        }
    }

    @Column(name="name")
    public String name;

    @ManyToOne
    @JoinColumn(name="type_id")
    private PetType petType;

    @ManyToOne  // Many pets can have one owner.
    @JoinColumn(name="owner_id")  //tells the JPA how the object will be mapped on DB level. We are expecting to have OWNER ID property on the pet record.
    private Owner owner;

    @Column(name="birth_date")
    private LocalDate birthDate;

    // pet can visit vet multiple times
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")  // pet properties maps back to pet in Visit class // CascadeType.ALL will delete visits associated to pet if pet id deleted
    private Set<Visit> visits = new HashSet<>(); // Set relation to visits and initialize set not to get NULL pointer exception

}
