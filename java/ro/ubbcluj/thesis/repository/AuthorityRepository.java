package ro.ubbcluj.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubbcluj.thesis.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
