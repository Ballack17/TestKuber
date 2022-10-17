package kubertest.master.data.mapper;

import kubertest.master.data.dto.EssenceEmailOffDto;
import kubertest.master.data.dto.FullEssenceDto;
import kubertest.master.data.entity.Essence;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface EssenceMapper {

    Essence toEssence(FullEssenceDto fullEssenceDto);

    @InheritInverseConfiguration
    FullEssenceDto toFullEssenceDto(Essence essence);

    EssenceEmailOffDto toEssenceEmailOffDto(Essence essence);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    Essence fromEssenceEmailOffDto(EssenceEmailOffDto essenceEmailOffDto);

}
