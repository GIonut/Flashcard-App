package ro.ubbcluj.thesis.service.mapper;

import java.util.Set;
import org.mapstruct.*;
import ro.ubbcluj.thesis.domain.AppUser;
import ro.ubbcluj.thesis.service.dto.AppUserDTO;

/**
 * Mapper for the entity {@link AppUser} and its DTO {@link AppUserDTO}.
 */
@Mapper(componentModel = "spring", uses = { UserMapper.class, PortalMapper.class })
public interface AppUserMapper extends EntityMapper<AppUserDTO, AppUser> {
    @Mapping(target = "appUser", source = "appUser", qualifiedByName = "id")
    @Mapping(target = "owner", source = "owner", qualifiedByName = "id")
    AppUserDTO toDto(AppUser s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AppUserDTO toDtoId(AppUser appUser);

    @Named("idSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    Set<AppUserDTO> toDtoIdSet(Set<AppUser> appUser);
}
