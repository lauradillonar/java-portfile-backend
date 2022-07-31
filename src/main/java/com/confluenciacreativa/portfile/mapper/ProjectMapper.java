package com.confluenciacreativa.portfile.mapper;

import com.confluenciacreativa.portfile.domain.Project;
import com.confluenciacreativa.portfile.entity.ProjectDB;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface ProjectMapper {

    @Mappings({
            @Mapping(source= "idProjectDB", target = "idProject"),
            @Mapping(source= "idPersonDB", target = "idPerson"),
            @Mapping(source= "titleDB", target = "title"),
            @Mapping(source= "fontawesomeDB", target = "fontawesome"),
            @Mapping(source= "letterDB", target = "letter"),
            @Mapping(source= "textDB", target = "text"),
            @Mapping(source= "viewmoreDB", target = "viewmore"),
            @Mapping(source= "personDB", target = "person"),

    })
    Project toProject(ProjectDB projectDB);
    List<Project> toProjects(List<ProjectDB> projectsDB);

    @InheritInverseConfiguration
    ProjectDB toProjectDB(Project project);
}
