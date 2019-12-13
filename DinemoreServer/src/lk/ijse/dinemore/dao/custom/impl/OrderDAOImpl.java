package lk.ijse.dinemore.dao.custom.impl;

import lk.ijse.dinemore.dao.CrudUtil;
import lk.ijse.dinemore.dao.custom.OrderDAO;
import lk.ijse.dinemore.entity.Customer;
import lk.ijse.dinemore.entity.Order;

import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean add(Order o) throws Exception {
        String sql="Insert into orders(cid,processTime,TOno,chefno,date,state) Values(?,?,?,?,curdate(),?)";
        return CrudUtil.execute(sql,o.getCustomer().getContact(),o.getProcessTime(),o.getTeleOperatorNo(),o.getChefNo(),o.getState());
    }

    @Override
    public boolean update(Order order) throws Exception {
        String sql="Update Orders set processTime=?,Chefno=?,state=? where id=?";
        return CrudUtil.execute(sql, Time.valueOf(order.getProcessTime()),order.getChefNo(), order.getState(), order.getId());
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        String sql="Delete from Orders where id=? && state=0";
        return CrudUtil.execute(sql,integer);
    }

    @Override
    public Order search(Integer integer) throws Exception {
        String sql="Select  * FROM orders where id=? && state=0";
        ResultSet rst=CrudUtil.execute(sql,integer);
        ArrayList<Order> orders = convertToOrder(rst);
        if(orders.size()>0) {
            return orders.get(0);
        }
        return null;
    }

    private ArrayList<Order> convertToOrder(ResultSet rst) throws Exception {
        ArrayList<Order> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Order(rst.getInt(1),
                    new Customer(rst.getString(2)),
                    (rst.getString(3)!=null)? LocalTime.parse(rst.getString(3)) :null,
                    rst.getString(4),
                    rst.getString(5),
                    (rst.getString(6)!=null)? LocalDate.parse(rst.getString(6)):null,
                    rst.getInt(7)));
        }
        return arrayList;
    }

    @Override
    public ArrayList<Order> getAll() throws Exception {
        String sql="Select * from Orders";
        ResultSet rst=CrudUtil.execute(sql);
        return convertToOrder(rst);
    }


    @Override
    public int getLastID() throws Exception {
        ArrayList<Order> all = getAll();
        if(all.size()>0) {
            Order order= all.get(all.size() - 1);
            return order.getId();
        }
        return 0;
    }

    @Override
    public ArrayList<Order> getAllUnFinishedOrders() throws Exception {
        String sql="Select * from Orders where state=0";
        ResultSet rst=CrudUtil.execute(sql);
        return convertToOrder(rst);
    }

    @Override
    public boolean updateStateto1(int od) throws Exception {
        String sql="Update orders set state=1 where id=?";
        return CrudUtil.execute(sql,od);
    }
}
