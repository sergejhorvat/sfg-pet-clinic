package guru.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="vets")
public class Vet extends Person {

    // ID is inherited from BaseEntity trough Person

    // it has to be initialized before adding specialty from DataLoader
    @ManyToMany(fetch = FetchType.EAGER) // By default @ManyToMany is initialized as lazy. Defining EAGER - JPA will try to load everything at once, otherwise specialties would be NULL!
    @JoinTable(name="vet_specialties",  // create join table for vets and specialities
            joinColumns = @JoinColumn(name="vet_id"), // one side of mapping from this object
            inverseJoinColumns = @JoinColumn(name="speciality_id")) // bidirectional relationship with one table
    private Set<Speciality> speciality = new HashSet<>();

    public Set<Speciality> getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Set<Speciality> speciality) {
        this.speciality = speciality;
    }
}
