package lk.ijse.dinemore.service;

import lk.ijse.dinemore.dto.EmployeeDTO;
import lk.ijse.dinemore.observer.Subject;

import java.rmi.Remote;

public interface SuperService<T> extends Remote, Subject {
    public EmployeeDTO login(String username,String password) throws Exception;
}
