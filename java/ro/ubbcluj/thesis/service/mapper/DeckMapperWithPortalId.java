package ro.ubbcluj.thesis.service.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.ubbcluj.thesis.domain.Deck;
import ro.ubbcluj.thesis.service.dto.DeckWithPortalIdDTO;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class DeckMapperWithPortalId {

    @Mapping(target = "portalId", source = "source")
    public abstract DeckWithPortalIdDTO map(Deck source);

    Long mapPortalId(Deck source) {
        if (source.getPortal() != null) return source.getPortal().getId();
        return null;
    }
}
