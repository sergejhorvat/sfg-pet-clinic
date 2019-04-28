package guru.springframework.sfgpetclinic.model;


// Specialty class for Vet
public class Speciality extends BaseEntity {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
