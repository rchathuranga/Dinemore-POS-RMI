package lk.ijse.dinemore.dao.custom;

import lk.ijse.dinemore.dao.CrudDAO;
import lk.ijse.dinemore.entity.Employee;

public interface EmployeeDAO extends CrudDAO<Employee,String> {
    Employee login(String username, String password) throws Exception;
}
