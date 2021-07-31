package pl.bejot.sfgpetclinic.services.map;

import org.springframework.stereotype.Service;
import pl.bejot.sfgpetclinic.model.Owner;
import pl.bejot.sfgpetclinic.model.Pet;
import pl.bejot.sfgpetclinic.model.PetType;
import pl.bejot.sfgpetclinic.services.OwnerService;
import pl.bejot.sfgpetclinic.services.PetService;
import pl.bejot.sfgpetclinic.services.PetTypeService;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
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
    public Owner save(Owner owner) {
        if (owner != null) {
            owner.getPets().forEach(pet -> {
                PetType petType = pet.getPetType();
                if (petType != null) {
                    if (petType.getId() == null) {
                        pet.setPetType(petTypeService.save(petType));
                    }
                } else {
                    throw new RuntimeException("PetType is required!");
                }

                if (pet.getId() == null) {
                    Pet savedPet = petService.save(pet);
                    pet.setId(savedPet.getId());
                }
            });

            return super.save(owner);
        }

        return null;
    }

    @Override
    public void delete(Owner t) {
        super.delete(t);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
