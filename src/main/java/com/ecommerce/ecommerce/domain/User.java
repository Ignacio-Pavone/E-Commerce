package domain;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class User {
    private Long id;
    private String name;
    private String password;
    private Role role;

    //Como manejar los permisos de los roles
    //TODO

    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
