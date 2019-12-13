package lk.ijse.dinemore.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class OrderDTO implements Serializable {
    private int id;
    private CustomerDTO customer;
    private ArrayList<OrderDetailDTO> orders;
    private LocalTime processTime;
    private String teleOperatorNo;
    private String chefNo;
    private LocalDate date;
    private int state;

    public OrderDTO() {
    }

    public OrderDTO(CustomerDTO customer, ArrayList<OrderDetailDTO> orders, LocalTime processTime, String teleOperatorNo, String chefNo, LocalDate date, int state) {
        this.customer = customer;
        this.orders = orders;
        this.processTime = processTime;
        this.teleOperatorNo = teleOperatorNo;
        this.chefNo = chefNo;
        this.date = date;
        this.state = state;
    }

    public OrderDTO(int id, CustomerDTO customer, ArrayList<OrderDetailDTO> orders, LocalTime processTime, String teleOperatorNo, String chefNo, LocalDate date, int state) {
        this.id = id;
        this.customer = customer;
        this.orders = orders;
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

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public ArrayList<OrderDetailDTO> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrderDetailDTO> orders) {
        this.orders = orders;
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
