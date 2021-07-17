package pl.bejot.sfgpetclinic.services;

import pl.bejot.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
