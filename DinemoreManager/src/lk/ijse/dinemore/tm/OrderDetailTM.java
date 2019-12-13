package lk.ijse.dinemore.tm;

import java.time.LocalDate;
import java.time.LocalTime;

public class OrderDetailTM {
    private int orderId;
    private String customerContact;
    private String customerName;
    private LocalTime processTime;
    private String telephoneOperator;
    private String chefNo;
    private LocalDate date;

    public OrderDetailTM(int orderId, String customerContact, String customerName, LocalTime processTime, String telephoneOperator, String chefNo, LocalDate date) {
        this.orderId = orderId;
        this.customerContact = customerContact;
        this.customerName = customerName;
        this.processTime = processTime;
        this.telephoneOperator = telephoneOperator;
        this.chefNo = chefNo;
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalTime getProcessTime() {
        return processTime;
    }

    public void setProcessTime(LocalTime processTime) {
        this.processTime = processTime;
    }

    public String getTelephoneOperator() {
        return telephoneOperator;
    }

    public void setTelephoneOperator(String telephoneOperator) {
        this.telephoneOperator = telephoneOperator;
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
}
