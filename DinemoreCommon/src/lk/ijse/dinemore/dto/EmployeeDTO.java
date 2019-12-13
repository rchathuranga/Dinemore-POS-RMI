package lk.ijse.dinemore.dto;

import java.io.Serializable;

public class EmployeeDTO implements Serializable {
    private String id;
    private String name;
    private String position;
    private String username;
    private String password;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String id, String name, String position, String username, String password) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
