package lk.ijse.dinemore.service.custom.impl;

import lk.ijse.dinemore.bo.BOFactory;
import lk.ijse.dinemore.bo.custom.EmployeeBO;
import lk.ijse.dinemore.bo.custom.OrderDetailBO;
import lk.ijse.dinemore.dto.EmployeeDTO;
import lk.ijse.dinemore.dto.OrderDTO;
import lk.ijse.dinemore.dto.OrderDetailDTO;
import lk.ijse.dinemore.observer.Observer;
import lk.ijse.dinemore.observer.ObserverImpl;
import lk.ijse.dinemore.service.custom.ManagerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ManagerServiceImpl extends UnicastRemoteObject implements ManagerService {

    EmployeeBO empBO= (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEE);
    OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);

    public ManagerServiceImpl() throws RemoteException {
    }

    @Override
    public boolean addEmployee(EmployeeDTO employeeDTO) throws Exception {
        return empBO.addEmployee(employeeDTO);
    }

    @Override
    public boolean deleteEmployee(String i) throws Exception {
        return empBO.deleteEmployee(i);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws Exception {
        return empBO.updateEmployee(employeeDTO);
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws Exception {
        return empBO.getAllEmployee();
    }

    @Override
    public EmployeeDTO searchEmployee(String id) throws Exception {
        return empBO.searchEmployee(id);
    }

    @Override
    public ArrayList<OrderDTO> getAllFinishedOrders() throws Exception {
        return orderDetailBO.getAllFinishedOrders();
    }

    @Override
    public ArrayList<OrderDTO> getAllUnFinishedWaitOrders() {
        try {
            return orderDetailBO.getAllUnFinishedOrders();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
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
