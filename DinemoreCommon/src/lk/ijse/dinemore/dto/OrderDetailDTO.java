package lk.ijse.dinemore.dto;

import java.io.Serializable;

public class OrderDetailDTO implements Serializable{

  private int id;
  private int orderID;
  private String item;
  private int qty;
  private double total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderID, String item, int qty) {
        this.orderID = orderID;
        this.item = item;
        this.qty = qty;
    }

    public OrderDetailDTO(String item, int qty) {
        this.item = item;
        this.qty = qty;
    }

    public OrderDetailDTO(int id, int orderID, String item, int qty) {
        this.id = id;
        this.orderID = orderID;
        this.item = item;
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}