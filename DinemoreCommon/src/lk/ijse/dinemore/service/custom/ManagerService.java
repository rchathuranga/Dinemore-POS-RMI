package lk.ijse.dinemore.service.custom;

import lk.ijse.dinemore.dto.EmployeeDTO;
import lk.ijse.dinemore.dto.OrderDTO;
import lk.ijse.dinemore.service.SuperService;

import java.util.ArrayList;

public interface ManagerService extends SuperService {
    boolean addEmployee(EmployeeDTO e) throws Exception;
    boolean deleteEmployee(String id) throws Exception;
    boolean updateEmployee(EmployeeDTO e) throws Exception;
    ArrayList<EmployeeDTO> getAllEmployee() throws Exception;
    EmployeeDTO searchEmployee(String id) throws Exception;
    ArrayList<OrderDTO> getAllFinishedOrders() throws Exception;
    ArrayList<OrderDTO> getAllUnFinishedWaitOrders() throws Exception;
}
