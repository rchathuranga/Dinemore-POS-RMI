package lk.ijse.dinemore.bo.custom;

import lk.ijse.dinemore.bo.SuperBO;
import lk.ijse.dinemore.dto.CustomerDTO;

import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    String addCustomer(CustomerDTO o) throws Exception;
    boolean deleteCustomer(String id) throws Exception;
    boolean updateCustomer(CustomerDTO o) throws Exception;
    CustomerDTO searchCustomer(String id) throws Exception;
    ArrayList<CustomerDTO> getAllCustomers() throws Exception;
}
