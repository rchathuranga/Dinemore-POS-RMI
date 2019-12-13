package lk.ijse.dinemore.bo.custom.impl;

import lk.ijse.dinemore.bo.custom.CustomerBO;
import lk.ijse.dinemore.dao.DAOFactory;
import lk.ijse.dinemore.dao.custom.CustomerDAO;
import lk.ijse.dinemore.dto.CustomerDTO;
import lk.ijse.dinemore.entity.Customer;

import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO cusDAO= (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public String addCustomer(CustomerDTO o) throws Exception {
        boolean add = cusDAO.add(new Customer(o.getContact(), o.getName()));
        if(add){return o.getContact();}
        return "";
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return cusDAO.delete(id);
    }

    @Override
    public boolean updateCustomer(CustomerDTO o) throws Exception {
        return cusDAO.update(new Customer(o.getContact(),o.getName()));
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        Customer cus = cusDAO.search(id);
        return (cus!=null)? new CustomerDTO(cus.getContact(),cus.getName()): null;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        ArrayList<Customer> all = cusDAO.getAll();
        ArrayList<CustomerDTO> customers=new ArrayList<>();
        for (Customer c :all) {
            customers.add(
                    new CustomerDTO(
                            c.getContact(),c.getName()
                    )
            );
        }
        return customers;
    }
}
