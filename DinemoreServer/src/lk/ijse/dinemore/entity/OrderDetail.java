package lk.ijse.dinemore.entity;

public class OrderDetail {
    private int id;
    private int orderID;
    private String item;
    private int qty;

    public OrderDetail() {
    }

    public OrderDetail(int id) {
        this.id = id;
    }

    public OrderDetail(int orderID, String item, int qty) {
        this.orderID = orderID;
        this.item = item;
        this.qty = qty;
    }




    public OrderDetail(int id, int orderID, String item, int qty) {
        this.id = id;
        this.orderID = orderID;
        this.item = item;
        this.qty = qty;
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
