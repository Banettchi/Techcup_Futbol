package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.Match;
import edu.eci.dosw.tech_cup.model.MatchModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    Match toEntity(MatchModel model);

    MatchModel toModel(Match entity);
}
