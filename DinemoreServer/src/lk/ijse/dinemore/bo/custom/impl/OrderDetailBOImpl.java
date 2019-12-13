package lk.ijse.dinemore.bo.custom.impl;


import lk.ijse.dinemore.bo.custom.OrderDetailBO;
import lk.ijse.dinemore.dao.DAOFactory;
import lk.ijse.dinemore.dao.custom.CustomerDAO;
import lk.ijse.dinemore.dao.custom.OrderDAO;
import lk.ijse.dinemore.dao.custom.OrderDetailDAO;
import lk.ijse.dinemore.dto.CustomerDTO;
import lk.ijse.dinemore.dto.OrderDTO;
import lk.ijse.dinemore.dto.OrderDetailDTO;
import lk.ijse.dinemore.entity.Customer;
import lk.ijse.dinemore.entity.Order;
import lk.ijse.dinemore.entity.OrderDetail;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    @Override
    public boolean addOrder(OrderDTO o) throws Exception {

        CustomerDTO cus = o.getCustomer();
        if (cus.isExist()) {
            return addingProcess(cus, o);
        } else {
            boolean addC = customerDAO.add(new Customer(cus.getContact(), cus.getName()));
            if (addC) {
                return addingProcess(cus, o);
            }
        }
        return false;
    }

    private boolean addingProcess(CustomerDTO cus, OrderDTO o) throws Exception {
        Order order = new Order(new Customer(cus.getContact()), null, o.getTeleOperatorNo(), null, LocalDate.now(), 0);
        boolean add = orderDAO.add(order);
        boolean complete = false;
        if (add) {
            for (OrderDetailDTO od : o.getOrders()) {
                complete = orderDetailDAO.add(new OrderDetail(
                        orderDAO.getLastID(), od.getItem(), od.getQty()
                ));
            }
            if (complete) {
                return true;
            }
        }
        return false;
    }

    @Override
    public OrderDTO getFirstOrder() throws Exception {
        ArrayList<Order> orders = orderDAO.getAllUnFinishedOrders();
        if(!orders.isEmpty()) {
            Order o = orders.get(0);
            orderDAO.updateStateto1(o.getId());
            Customer search = customerDAO.search(o.getCustomer().getContact());
            ArrayList<OrderDetail> all = orderDetailDAO.getAll(o.getId());
            OrderDTO orderDTO = new OrderDTO(o.getId(), new CustomerDTO(search.getContact(), search.getName()), convertToDTO(all),
                    o.getProcessTime(), o.getTeleOperatorNo(), o.getChefNo(), o.getDate(), o.getState()
            );
            return orderDTO;
        }
        return null;
    }

    private ArrayList<OrderDetailDTO> convertToDTO(ArrayList<OrderDetail> all) {
        ArrayList<OrderDetailDTO> arrayList = new ArrayList<>();
        for (OrderDetail o : all) {
            arrayList.add(new OrderDetailDTO(
                    o.getId(), o.getOrderID(), o.getItem(), o.getQty()
            ));
        }

        return arrayList;
    }

    @Override
    public boolean deleteOrder(int id) throws Exception {
        return orderDAO.delete(id);
    }

    @Override
    public boolean finishOrder(OrderDTO o) throws Exception {
        return orderDAO.update(new Order(
                o.getId(), new Customer(o.getCustomer().getContact()), o.getProcessTime(), o.getTeleOperatorNo(), o.getChefNo(),
                o.getDate(), o.getState()
        ));
    }

    @Override
    public boolean updateWaitOrder(OrderDTO orderDTO) throws Exception {
        ArrayList<OrderDetailDTO> orders = orderDTO.getOrders();
        boolean updated = false;
        for (OrderDetailDTO o : orders) {
            updated = orderDetailDAO.update(new OrderDetail(o.getId(), o.getOrderID(), o.getItem(), o.getQty()));
        }
        return updated;
    }

    @Override
    public int getLastOrderID() throws Exception {
        return orderDAO.getLastID();
    }

    @Override
    public OrderDTO searchUnFinishOrder(int id) throws Exception {
        Order o = orderDAO.search(id);
        if (null != o) {
            ArrayList<OrderDetail> all = orderDetailDAO.getAll(o.getId());
            Customer c = o.getCustomer();
            return new OrderDTO(o.getId(), new CustomerDTO(c.getContact(), c.getName()), convertToDTO(all),
                    o.getProcessTime(), o.getTeleOperatorNo(), o.getChefNo(), o.getDate(), o.getState());
        }
        return null;
    }

    @Override
    public ArrayList<OrderDTO> getAllFinishedOrders() throws Exception {
        ArrayList<Order> all = orderDAO.getAll();
        return convertToOrderDTO(all);
    }

    private ArrayList<OrderDTO> convertToOrderDTO(ArrayList<Order> list) throws Exception{
        ArrayList<OrderDTO> arrayList=new ArrayList<>();
        for (Order o: list) {
            Customer search = customerDAO.search(o.getCustomer().getContact());
            arrayList.add(new OrderDTO(
                    o.getId(),new CustomerDTO(o.getCustomer().getContact(),search.getName()),null,
                    o.getProcessTime(),o.getTeleOperatorNo(),o.getChefNo(),o.getDate(),o.getState()
            ));
        }
        return arrayList;
    }

    @Override
    public ArrayList<OrderDTO> getAllUnFinishedOrders() throws Exception {
        ArrayList<Order> all = orderDAO.getAllUnFinishedOrders();
        return convertToOrderDTO(all);
    }
}
