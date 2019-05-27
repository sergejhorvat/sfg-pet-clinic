package guru.springframework.sfgpetclinic.model;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="owners")
public class Owner extends Person {

    @Builder // First 3 params are coming from Person class so constructor must be in Person class ans in BaseEntity class
    public Owner(String firstName, String lastName, String address,
                 String city, String telephone, Set<Pet> pets) {
        super(firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
    }



    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="telephone")
    private String telephone;

    // One owner can have multiple pets
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner") // Cascade type ALL means that if owner gets deleted the Pet is also deleted. So the child object is removed.
    private Set<Pet> pets = new HashSet<>(); // initialize object so we do not get null pointer exception
}
