package edu.eci.dosw.tech_cup.model;

import java.util.ArrayList;
import java.util.List;

public class RoleModel {

    private Long id;
    private String name;
    private List<PermissionModel> permissions = new ArrayList<>();

    public RoleModel() {}

    public RoleModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }



    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<PermissionModel> getPermissions() { return permissions; }
    public void setPermissions(List<PermissionModel> permissions) { this.permissions = permissions; }
}
