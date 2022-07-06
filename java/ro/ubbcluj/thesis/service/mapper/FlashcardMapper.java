package ro.ubbcluj.thesis.service.mapper;

import org.mapstruct.*;
import ro.ubbcluj.thesis.domain.Flashcard;
import ro.ubbcluj.thesis.service.dto.FlashcardDTO;

/**
 * Mapper for the entity {@link Flashcard} and its DTO {@link FlashcardDTO}.
 */
@Mapper(componentModel = "spring", uses = { DeckMapper.class })
public interface FlashcardMapper extends EntityMapper<FlashcardDTO, Flashcard> {
    @Mapping(target = "deck", source = "deck", qualifiedByName = "id")
    FlashcardDTO toDto(Flashcard s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FlashcardDTO toDtoId(Flashcard flashcard);
}
