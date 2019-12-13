package lk.ijse.dinemore.entity;

public class Customer {
    private String contact;
    private String name;

    public Customer() {
    }

    public Customer(String contact) {
        this.contact = contact;
    }

    public Customer(String contact, String name) {
        this.contact = contact;
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
