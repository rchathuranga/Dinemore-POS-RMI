package lk.ijse.dinemore.service.custom;

import lk.ijse.dinemore.dto.CustomerDTO;
import lk.ijse.dinemore.dto.OrderDTO;
import lk.ijse.dinemore.reservation.Reservation;
import lk.ijse.dinemore.service.SuperService;

public interface TeleOperatorService extends SuperService, Reservation {
    CustomerDTO searchCustomer(String id) throws Exception;
    boolean addWaitOrder(OrderDTO o) throws Exception;
    boolean updateWaitOrder(OrderDTO o) throws Exception;
    int getLastOrderID() throws Exception;
    OrderDTO searchWaitingOrder(int id) throws Exception;
    boolean cancelOrder(OrderDTO o) throws Exception;
}
