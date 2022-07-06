package ro.ubbcluj.thesis.service.mapper;

import java.util.Set;
import org.mapstruct.*;
import ro.ubbcluj.thesis.domain.Portal;
import ro.ubbcluj.thesis.service.dto.PortalDTO;

/**
 * Mapper for the entity {@link Portal} and its DTO {@link PortalDTO}.
 */
@Mapper(componentModel = "spring", uses = { AppUserMapper.class })
public interface PortalMapper extends EntityMapper<PortalDTO, Portal> {
    @Mapping(target = "followers", source = "followers", qualifiedByName = "idSet")
    PortalDTO toDto(Portal s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PortalDTO toDtoId(Portal portal);

    @Mapping(target = "removeFollower", ignore = true)
    Portal toEntity(PortalDTO portalDTO);
}
