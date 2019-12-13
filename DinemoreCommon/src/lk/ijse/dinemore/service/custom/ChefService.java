package lk.ijse.dinemore.service.custom;

import lk.ijse.dinemore.dto.OrderDTO;
import lk.ijse.dinemore.service.SuperService;

public interface ChefService extends SuperService<OrderDTO> {
    OrderDTO getFirstOrder() throws Exception;
    boolean finishOrder(OrderDTO o) throws Exception;
}
