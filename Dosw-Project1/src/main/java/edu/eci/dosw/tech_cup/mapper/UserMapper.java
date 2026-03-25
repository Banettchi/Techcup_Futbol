package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.User;
import edu.eci.dosw.tech_cup.model.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserModel model);

    UserModel toModel(User entity);
}
