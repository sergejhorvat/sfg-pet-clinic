package guru.springframework.sfgpetclinic.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="owners")
public class Owner extends Person {

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="telephone")
    private String telephone;

    // One owner can have multiple pets
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner") // Cascade type ALL means that if owner gets deleted the Pet is also deleted. So the child object is removed.
    private Set<Pet> pets = new HashSet<>(); // initialize object so we do not get null pointer exception

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
