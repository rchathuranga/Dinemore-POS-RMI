package lk.ijse.dinemore.dao.custom.impl;

import lk.ijse.dinemore.dao.CrudUtil;
import lk.ijse.dinemore.dao.custom.CustomerDAO;
import lk.ijse.dinemore.entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer c) throws Exception {
        String sql="Insert into Customer Values (?,?)";
        return CrudUtil.execute(sql,c.getContact(),c.getName());
    }

    @Override
    public boolean update(Customer c) throws Exception {
        String sql="Update Customer set name=? where contact=?";
        return CrudUtil.execute(sql,c.getName(),c.getContact());
    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql="Delete from customer where contact=?";
        return CrudUtil.execute(sql,id);
    }

    @Override
    public Customer search(String id) throws Exception {
        String sql="Select * from Customer where contact=?";
        ResultSet rst = CrudUtil.execute(sql, id);
        Customer customer=null;
        if(rst.next()){
            customer=new Customer(rst.getString(1),rst.getString(2));
        }
        return customer;
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        String sql="Select * from Customer";
        ResultSet rst= CrudUtil.execute(sql);
        ArrayList<Customer> customers=new ArrayList<>();

        while (rst.next()){
            customers.add(new Customer(
               rst.getString(2),rst.getString(3)
            ));
        }
        return customers;
    }
}
