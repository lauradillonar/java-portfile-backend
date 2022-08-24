package com.confluenciacreativa.portfile.mapper;

import com.confluenciacreativa.portfile.domain.Education;
import com.confluenciacreativa.portfile.entity.EducationDB;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface EducationMapper {
    @Mappings({
            @Mapping(source="idEducationDB", target = "idEducation"),
            @Mapping(source="idPersonDB", target = "idPerson"),
            @Mapping(source="titleDB", target = "title"),
            @Mapping(source="whenDB", target = "when"),
            @Mapping(source="subtitleDB", target = "subtitle"),
            @Mapping(source="text1DB", target = "text1"),
            @Mapping(source="linkDB", target = "link"),
            @Mapping(source="urlDB", target = "url"),
            @Mapping(source="text2DB", target = "text2"),
            @Mapping(source="viewmoreDB", target = "viewmore")
    })
    Education toEducation(EducationDB educationDB);
    List<Education> toEducations(List<EducationDB> educationsDB);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "personDB", ignore = true)
    })
    EducationDB toEducationDB(Education education);
}
