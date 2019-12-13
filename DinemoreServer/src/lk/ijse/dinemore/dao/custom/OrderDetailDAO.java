package lk.ijse.dinemore.dao.custom;

import lk.ijse.dinemore.dao.CrudDAO;
import lk.ijse.dinemore.entity.Order;
import lk.ijse.dinemore.entity.OrderDetail;

import java.util.ArrayList;

public interface OrderDetailDAO extends CrudDAO<OrderDetail,Integer> {
    ArrayList<OrderDetail> getAll(int i) throws Exception;
}
