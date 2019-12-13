package lk.ijse.dinemore.dto;

import java.io.Serializable;

public class CustomerDTO implements Serializable {
    private String contact;
    private String name;
    private boolean isExist;

    public CustomerDTO() {
    }

    public CustomerDTO(String contact, String name) {
        this.contact = contact;
        this.name = name;
    }

    public CustomerDTO(String contact, String name, boolean isExist) {
        this.contact = contact;
        this.name = name;
        this.isExist = isExist;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
