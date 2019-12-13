package lk.ijse.dinemore.dao.custom;


import lk.ijse.dinemore.dao.CrudDAO;
import lk.ijse.dinemore.entity.Order;

import java.util.ArrayList;


public interface OrderDAO extends CrudDAO<Order,Integer> {
    int getLastID() throws Exception;
    ArrayList<Order> getAllUnFinishedOrders() throws Exception;
    boolean updateStateto1(int od) throws Exception;
}
