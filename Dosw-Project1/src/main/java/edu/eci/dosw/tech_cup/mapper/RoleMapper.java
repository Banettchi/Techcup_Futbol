package edu.eci.dosw.tech_cup.mapper;

import edu.eci.dosw.tech_cup.entity.Permission;
import edu.eci.dosw.tech_cup.entity.Role;
import edu.eci.dosw.tech_cup.model.PermissionModel;
import edu.eci.dosw.tech_cup.model.RoleModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toEntity(RoleModel model);

    RoleModel toModel(Role entity);

    Permission toPermissionEntity(PermissionModel model);

    PermissionModel toPermissionModel(Permission entity);
}
