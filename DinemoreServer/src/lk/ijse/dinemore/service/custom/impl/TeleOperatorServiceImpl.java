package lk.ijse.dinemore.service.custom.impl;

import lk.ijse.dinemore.bo.BOFactory;
import lk.ijse.dinemore.bo.custom.CustomerBO;
import lk.ijse.dinemore.bo.custom.EmployeeBO;
import lk.ijse.dinemore.bo.custom.OrderDetailBO;
import lk.ijse.dinemore.dto.CustomerDTO;
import lk.ijse.dinemore.dto.EmployeeDTO;
import lk.ijse.dinemore.dto.OrderDTO;
import lk.ijse.dinemore.dto.OrderDetailDTO;
import lk.ijse.dinemore.observer.Observer;
import lk.ijse.dinemore.observer.ObserverImpl;
import lk.ijse.dinemore.reservation.ReservationImpl;
import lk.ijse.dinemore.service.custom.TeleOperatorService;

import java.rmi.server.UnicastRemoteObject;


public class TeleOperatorServiceImpl extends UnicastRemoteObject implements TeleOperatorService {

    private OrderDetailBO orderDetailBO;
    private CustomerBO customerBO;
    private EmployeeBO empDAO;
    private static ReservationImpl<TeleOperatorService> reservation;

    public TeleOperatorServiceImpl() throws Exception {
        reservation= new ReservationImpl<>();
        orderDetailBO = (OrderDetailBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);
        customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
        empDAO= (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEE);
        System.out.println(getClientHost());
    }

    @Override
    public EmployeeDTO login(String s, String s1) throws Exception {
        return empDAO.login(s, s1);
    }

    @Override
    public boolean updateWaitOrder(OrderDTO order) throws Exception {
        release(order.getId());
        return orderDetailBO.updateWaitOrder(order);
    }

    @Override
    public CustomerDTO searchCustomer(String i) throws Exception {
        return customerBO.searchCustomer(i);
    }

    @Override
    public boolean addWaitOrder(OrderDTO order) throws Exception {
        return orderDetailBO.addOrder(order);
    }

    @Override
    public int getLastOrderID() throws Exception {
        return orderDetailBO.getLastOrderID();
    }

    @Override
    public OrderDTO searchWaitingOrder(int i) throws Exception {
        if(!isReserved(i)) {
            reserve(i);
            return orderDetailBO.searchUnFinishOrder(i);
        }
        return null;
    }

    @Override
    public boolean cancelOrder(OrderDTO orderDTO) throws Exception {
        release(orderDTO.getId());
        return orderDetailBO.deleteOrder(orderDTO.getId());
    }

    @Override
    public boolean reserve(Object o) throws Exception {
        return reservation.reserve(o,this);
    }

    @Override
    public boolean release(Object o) throws Exception {
        return reservation.release(o,this);
    }

    @Override
    public boolean isReserved(Object o) throws Exception {
        return reservation.isReserved(o);
    }

    @Override
    public void register(Observer observer) throws Exception {
        new ObserverImpl().register(observer);
    }

    @Override
    public void unRegister(Observer observer) throws Exception {
        new ObserverImpl().unRegister(observer);
    }

    @Override
    public void notifyObservers() throws Exception {
        new ObserverImpl().notifyObservers();
    }
}