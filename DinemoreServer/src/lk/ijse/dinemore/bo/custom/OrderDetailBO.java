package lk.ijse.dinemore.bo.custom;

import lk.ijse.dinemore.bo.SuperBO;
import lk.ijse.dinemore.dto.OrderDTO;
import lk.ijse.dinemore.dto.OrderDetailDTO;
import lk.ijse.dinemore.entity.Order;

import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {

    boolean addOrder(OrderDTO o) throws Exception;

    OrderDTO getFirstOrder() throws Exception;

    boolean deleteOrder(int id) throws Exception;

    boolean finishOrder(OrderDTO o) throws Exception;

    boolean updateWaitOrder(OrderDTO o) throws Exception;

    int getLastOrderID() throws Exception;

    OrderDTO searchUnFinishOrder(int id) throws Exception;

    ArrayList<OrderDTO> getAllFinishedOrders() throws Exception;

    ArrayList<OrderDTO> getAllUnFinishedOrders() throws Exception;
}
