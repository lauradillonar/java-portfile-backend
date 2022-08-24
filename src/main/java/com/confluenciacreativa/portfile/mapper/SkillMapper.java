package com.confluenciacreativa.portfile.mapper;

import com.confluenciacreativa.portfile.domain.Skill;
import com.confluenciacreativa.portfile.entity.SkillDB;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface SkillMapper {
    @Mappings({
            @Mapping(source = "idSkillDB", target = "idSkill"),
            @Mapping(source = "idPersonDB", target = "idPerson"),
            @Mapping(source = "itemDB", target = "item"),
            @Mapping(source = "progressDB", target = "progress")
    })
    Skill toSkill(SkillDB skillDB);
    List<Skill> toSkills(List<SkillDB> skillsDB);

    @InheritInverseConfiguration
    @Mappings({
        @Mapping(target = "personDB", ignore = true)
    })
    SkillDB toSkillDB(Skill skill);
}
