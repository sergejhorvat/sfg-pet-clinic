package guru.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass // it will not be created in DB it will be inherited
public class Person extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
