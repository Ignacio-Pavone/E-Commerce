package domain;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Getter
@Setter
public class Role {

    RoleType role;
    List<Permission> permissions;
    public Role(RoleType role) {
        this.role = role;
        this.permissions = new ArrayList<>();
    }
    public void addPermission(Permission... permission) {
        Collections.addAll(permissions, permission);
    }

    @Override
    public String toString() {
        return  role + ", permissions=" + permissions +
                '}';
    }
}

