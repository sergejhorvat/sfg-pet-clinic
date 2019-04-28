package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    // Removed, so dependency are injected via annotation of classes
    // OwnerServiceMap, PetServiceMap, VerServiceMap as @Service
   /* public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();

    }*/
   // New constructor for dependency, spring context is use to load dependency
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;

        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        //owner1.setId(1L);
        owner1.setFirstName("Michel");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Kostrena");
        owner1.setCity("Kostrena");
        owner1.setTelephone("213213213213");
        ownerService.save(owner1);

        Pet mikesPet = new Pet();
        mikesPet.setPetType(saveDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet); // set association on both ends Pet <-> Owner


        Owner owner2 = new Owner();
        //owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner1.setAddress("Braće Milih 2");
        owner1.setCity("Matulji");
        owner1.setTelephone("22323213");
        ownerService.save(owner2);

        Pet fionasCat = new Pet();
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setName("Some cat");
        fionasCat.setPetType(saveCatPetType);
        owner2.getPets().add(fionasCat);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        //vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);


        Vet vet2 = new Vet();
        //vet2.setId(2L);
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
