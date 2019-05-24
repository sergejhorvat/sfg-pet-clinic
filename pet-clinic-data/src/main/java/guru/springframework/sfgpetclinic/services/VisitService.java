package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitService extends CrudService<Visit, Long> {
}
