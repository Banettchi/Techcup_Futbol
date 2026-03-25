package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.Tournament;
import edu.eci.dosw.tech_cup.model.TournamentModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TournamentMapper {

    Tournament toEntity(TournamentModel model);

    TournamentModel toModel(Tournament entity);
}
