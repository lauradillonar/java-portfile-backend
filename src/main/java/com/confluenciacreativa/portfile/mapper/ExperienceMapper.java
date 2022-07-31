package com.confluenciacreativa.portfile.mapper;

import com.confluenciacreativa.portfile.domain.Experience;
import com.confluenciacreativa.portfile.entity.ExperienceDB;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface ExperienceMapper {
    @Mappings({
            @Mapping(source="idExperienceDB", target = "idExperience"),
            @Mapping(source="idPersonDB", target = "idPerson"),
            @Mapping(source="titleDB", target = "title"),
            @Mapping(source="titleDB", target = "subtitle"),
            @Mapping(source="whenDB", target = "when"),
            @Mapping(source="whereDB", target = "where"),
            @Mapping(source="whereDB", target = "text"),
            @Mapping(source="link1DB", target = "link1"),
            @Mapping(source="url1DB", target = "url1"),
            @Mapping(source="link2DB", target = "link2"),
            @Mapping(source="url2DB", target = "url2"),
            @Mapping(source="link3DB", target = "link3"),
            @Mapping(source="url3DB", target = "url3"),
            @Mapping(source="personDB", target = "person")
    })
    Experience toExperience(ExperienceDB experienceDB);
    List<Experience> toExperiences(List<ExperienceDB> experiencesDB);

    @InheritInverseConfiguration
    ExperienceDB toExperienceDB(Experience experience);
}
