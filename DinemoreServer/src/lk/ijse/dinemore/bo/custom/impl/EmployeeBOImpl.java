package lk.ijse.dinemore.bo.custom.impl;

import lk.ijse.dinemore.bo.custom.EmployeeBO;
import lk.ijse.dinemore.dao.DAOFactory;
import lk.ijse.dinemore.dao.custom.EmployeeDAO;
import lk.ijse.dinemore.dto.EmployeeDTO;
import lk.ijse.dinemore.entity.Employee;

import java.util.ArrayList;
import java.util.Base64;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO empDAO= (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public boolean addEmployee(EmployeeDTO e) throws Exception {
        String username = Base64.getEncoder().encodeToString(e.getUsername().getBytes("UTF-8"));
        String password = Base64.getEncoder().encodeToString(e.getPassword().getBytes("UTF-8"));
        return empDAO.add(new Employee(e.getId(), e.getName(), e.getPosition(),username, password));
    }

    @Override
    public EmployeeDTO login(String username, String password) throws Exception {
        String eUsername = Base64.getEncoder().encodeToString(username.getBytes("UTF-8"));
        String ePassword = Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));

        Employee login = empDAO.login(eUsername, ePassword);
        if(login!=null) {
            String user = new String(Base64.getDecoder().decode(login.getUsername().getBytes("UTF-8")));
            String pass = new String(Base64.getDecoder().decode(login.getPassword().getBytes("UTF-8")));
            return new EmployeeDTO(login.getId(), login.getName(), login.getPosition(), user, pass);
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(String e) throws Exception {
        return empDAO.delete(e);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO e) throws Exception {
        String username = Base64.getEncoder().encodeToString(e.getUsername().getBytes("UTF-8"));
        String password = Base64.getEncoder().encodeToString(e.getPassword().getBytes("UTF-8"));
        return empDAO.update(new Employee(e.getId(), e.getName(), e.getPosition(), username, password));
    }

    @Override
    public EmployeeDTO searchEmployee(String id) throws Exception {
        Employee s = empDAO.search(id);
        if (s!=null) {
            String username = new String(Base64.getDecoder().decode(s.getUsername().getBytes()));
            String password= new String(Base64.getDecoder().decode(s.getPassword().getBytes()));
            return new EmployeeDTO(s.getId(), s.getName(), s.getPosition(), username, password);
        }
        return null;
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws Exception {
        ArrayList<EmployeeDTO> arrayList=new ArrayList<>();
        ArrayList<Employee> employees =empDAO.getAll();
        for (Employee e :employees) {
            arrayList.add(new EmployeeDTO(e.getId(), e.getName(), e.getPosition(), e.getUsername(), e.getPassword()));
        }
        return arrayList;
    }
}
