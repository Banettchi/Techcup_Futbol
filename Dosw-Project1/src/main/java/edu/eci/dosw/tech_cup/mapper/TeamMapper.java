package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.Team;
import edu.eci.dosw.tech_cup.model.TeamModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    Team toEntity(TeamModel model);

    TeamModel toModel(Team entity);
}
