package pl.bejot.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.bejot.sfgpetclinic.model.Owner;
import pl.bejot.sfgpetclinic.model.Pet;
import pl.bejot.sfgpetclinic.model.PetType;
import pl.bejot.sfgpetclinic.model.Vet;
import pl.bejot.sfgpetclinic.services.OwnerService;
import pl.bejot.sfgpetclinic.services.PetTypeService;
import pl.bejot.sfgpetclinic.services.VetService;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        System.out.println("Loaded pet types...");

        Owner owner = new Owner();
        owner.setFirstName("Robert");
        owner.setLastName("Downey Junior");
        owner.setAddress("Wojska Polskiego 2");
        owner.setCity("Pruszcz Gdański");
        owner.setTelephone("123123123");

        Pet robertsPet = new Pet();
        robertsPet.setName("Marvel");
        robertsPet.setPetType(savedDogPetType);
        robertsPet.setBirthDate(LocalDate.now());
        robertsPet.setOwner(owner);
        owner.getPets().add(robertsPet);
        ownerService.save(owner);

        Owner owner2 = new Owner();
        owner2.setFirstName("Chris");
        owner2.setLastName("Evans");
        owner2.setAddress("3 Maja 4");
        owner2.setCity("Gdańsk");
        owner2.setTelephone("456456456");

        Pet chrisPet = new Pet();
        chrisPet.setName("Tosiek");
        chrisPet.setPetType(savedCatPetType);
        chrisPet.setBirthDate(LocalDate.now());
        chrisPet.setOwner(owner2);
        owner2.getPets().add(chrisPet);
        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet = new Vet();
        vet.setFirstName("Chris");
        vet.setLastName("Hemsworth");
        vetService.save(vet);

        Vet vet2 = new Vet();
        vet2.setFirstName("Scarlett");
        vet2.setLastName("Johansson");
        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
