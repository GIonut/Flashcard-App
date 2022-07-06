package ro.ubbcluj.thesis.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import ro.ubbcluj.thesis.domain.Portal;

public interface PortalRepositoryWithBagRelationships {
    Optional<Portal> fetchBagRelationships(Optional<Portal> portal);

    List<Portal> fetchBagRelationships(List<Portal> portals);

    Page<Portal> fetchBagRelationships(Page<Portal> portals);
}
