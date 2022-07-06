package ro.ubbcluj.thesis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ro.ubbcluj.thesis.domain.AppUser;
import ro.ubbcluj.thesis.domain.User;

/**
 * Spring Data SQL repository for the AppUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>, JpaSpecificationExecutor<AppUser> {
    @Query("SELECT au from AppUser au where au.appUser = ?1")
    AppUser findByAppUser(User user);
}
