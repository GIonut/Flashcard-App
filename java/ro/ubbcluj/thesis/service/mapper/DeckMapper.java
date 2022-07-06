package ro.ubbcluj.thesis.service.mapper;

import org.mapstruct.*;
import ro.ubbcluj.thesis.domain.Deck;
import ro.ubbcluj.thesis.service.dto.DeckDTO;

/**
 * Mapper for the entity {@link Deck} and its DTO {@link DeckDTO}.
 */
@Mapper(componentModel = "spring", uses = { PortalMapper.class })
public interface DeckMapper extends EntityMapper<DeckDTO, Deck> {
    @Mapping(target = "portal", source = "portal", qualifiedByName = "id")
    DeckDTO toDto(Deck s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DeckDTO toDtoId(Deck deck);
}
