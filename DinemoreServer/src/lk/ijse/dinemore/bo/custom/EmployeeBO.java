package lk.ijse.dinemore.bo.custom;

import lk.ijse.dinemore.bo.SuperBO;
import lk.ijse.dinemore.dto.EmployeeDTO;

import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    boolean addEmployee(EmployeeDTO e) throws Exception;

    EmployeeDTO login(String username, String password) throws Exception;

    boolean deleteEmployee(String e) throws Exception;

    boolean updateEmployee(EmployeeDTO e) throws Exception;

    EmployeeDTO searchEmployee(String id) throws Exception;

    ArrayList<EmployeeDTO> getAllEmployee() throws Exception;
}
