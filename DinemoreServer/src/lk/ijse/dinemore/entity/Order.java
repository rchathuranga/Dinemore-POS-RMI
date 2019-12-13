package lk.ijse.dinemore.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Order {
    private int id;
    private Customer customer;
    private LocalTime processTime;
    private String teleOperatorNo;
    private String chefNo;
    private LocalDate date;
    private int state;

    public Order() {
    }

    public Order(Customer customer, LocalTime processTime, String teleOperatorNo, String chefNo, LocalDate date, int state) {
        this.customer = customer;
        this.processTime = processTime;
        this.teleOperatorNo = teleOperatorNo;
        this.chefNo = chefNo;
        this.date = date;
        this.state = state;
    }

    public Order(int id, Customer customer, LocalTime processTime, String teleOperatorNo, String chefNo, LocalDate date, int state) {
        this.id = id;
        this.customer = customer;
        this.processTime = processTime;
        this.teleOperatorNo = teleOperatorNo;
        this.chefNo = chefNo;
        this.date = date;
        this.state = state;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalTime getProcessTime() {
        return processTime;
    }

    public void setProcessTime(LocalTime processTime) {
        this.processTime = processTime;
    }

    public String getTeleOperatorNo() {
        return teleOperatorNo;
    }

    public void setTeleOperatorNo(String teleOperatorNo) {
        this.teleOperatorNo = teleOperatorNo;
    }

    public String getChefNo() {
        return chefNo;
    }

    public void setChefNo(String chefNo) {
        this.chefNo = chefNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
