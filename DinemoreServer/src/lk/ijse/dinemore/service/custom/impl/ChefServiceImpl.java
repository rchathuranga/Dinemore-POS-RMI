package lk.ijse.dinemore.service.custom.impl;

import lk.ijse.dinemore.bo.BOFactory;
import lk.ijse.dinemore.bo.custom.EmployeeBO;
import lk.ijse.dinemore.bo.custom.OrderDetailBO;
import lk.ijse.dinemore.dto.EmployeeDTO;
import lk.ijse.dinemore.dto.OrderDTO;
import lk.ijse.dinemore.dto.OrderDetailDTO;
import lk.ijse.dinemore.observer.Observer;
import lk.ijse.dinemore.observer.ObserverImpl;
import lk.ijse.dinemore.service.custom.ChefService;

import java.rmi.server.UnicastRemoteObject;

public class ChefServiceImpl extends UnicastRemoteObject implements ChefService {

    private OrderDetailBO bo;
    private EmployeeBO empBO;

    public ChefServiceImpl() throws Exception {
        bo = (OrderDetailBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);
        empBO= (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEE);
        System.out.println(getClientHost());
    }

    @Override
    public OrderDTO getFirstOrder() {
        try {
            return bo.getFirstOrder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean finishOrder(OrderDTO o) throws Exception {
        return bo.finishOrder(o);
    }

    @Override
    public EmployeeDTO login(String s, String s1) throws Exception {
        return empBO.login(s,s1);
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
