package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService{

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) { // use same id's from PetType and Pet object into Owner object! Check and set function:
        if (object != null){ // if there is a Owner to save
            if(object.getPets() != null){  // and it has pets
                object.getPets().forEach(pet -> {  // check for all pets
                    if (pet.getPetType() != null){ // if they do have PetType defined if not goto exception
                        if(pet.getPetType().getId() == null){  // PetType for pet has not been saved yet
                            pet.setPetType(petTypeService.save(pet.getPetType()));  // its taken from object and saved
                                                                                    // to get an id.
                        }
                    }else{ // if there is no PetType in Pet object throw exception
                        throw new RuntimeException("Pet Type is required!");
                    }
                    if(pet.getId() == null){ // checkinh it the pet object has an id
                        Pet savedPet = petService.save(pet); // get id generated while saving temp object
                        pet.setId(savedPet.getId()); // get id from temp object and set it to pet
                    }
                });
            }
            return super.save(object);
        }else{
            return null;
        }
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }
}
