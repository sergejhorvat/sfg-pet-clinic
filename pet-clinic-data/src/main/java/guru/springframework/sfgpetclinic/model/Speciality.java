package guru.springframework.sfgpetclinic.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

// Specialty class for Vet
@Entity
@Table(name="specialities")
public class Speciality extends BaseEntity {

    // ID will be inherited from the BaseEntity class

    @Column(name="description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
