package lk.ijse.dinemore.dao.custom.impl;

import lk.ijse.dinemore.dao.CrudUtil;
import lk.ijse.dinemore.dao.custom.EmployeeDAO;
import lk.ijse.dinemore.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean add(Employee e) throws Exception {
        String sql="INSERT INTO employee VALUES (?,?,?,?,?)";
        return CrudUtil.execute(sql,e.getId(),e.getName(),e.getPosition(),e.getUsername(),e.getPassword());
    }

    @Override
    public boolean update(Employee employee) throws Exception {
        String sql="UPDATE employee SET name=?, username=?, password=? WHERE id=?";
        return CrudUtil.execute(sql,employee.getName(),employee.getUsername(),employee.getPassword(),employee.getId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql="DELETE FROM employee WHERE id=?";
        return CrudUtil.execute(sql,id);
    }

    @Override
    public Employee search(String id) throws Exception {
        String sql="Select * FROM employee WHERE id=?";
        ResultSet rst= CrudUtil.execute(sql,id);
        ArrayList<Employee> arrayList = convertToEmployee(rst);
        if(arrayList.size()>0) {
            return arrayList.get(0);
        }
        return null;
    }

    @Override
    public ArrayList<Employee> getAll() throws Exception {
        String sql="Select id,name,position from employee";
        ResultSet rst = CrudUtil.execute(sql);
        ArrayList<Employee> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Employee(rst.getString(1),rst.getString(2),rst.getString(3)));
        }
        return arrayList;
    }

    @Override
    public Employee login(String username, String password) throws Exception {
        String sql="Select * FROM employee WHERE username=? && password=?";
        ResultSet rst= CrudUtil.execute(sql,username,password);
        ArrayList<Employee> arrayList = convertToEmployee(rst);
        if(arrayList.size()>0) {
            return arrayList.get(0);
        }
        return null;
    }

    private ArrayList<Employee> convertToEmployee(ResultSet rst) throws SQLException {
        ArrayList<Employee> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Employee(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4),rst.getString(5)));
        }
        return arrayList;
    }
}
