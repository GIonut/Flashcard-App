package ro.ubbcluj.thesis.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import ro.ubbcluj.thesis.domain.AppUser;

public interface AppUserRepositoryWithBagRelationships {
    Optional<AppUser> fetchBagRelationships(Optional<AppUser> appUser);

    List<AppUser> fetchBagRelationships(List<AppUser> appUsers);

    Page<AppUser> fetchBagRelationships(Page<AppUser> appUsers);
}
