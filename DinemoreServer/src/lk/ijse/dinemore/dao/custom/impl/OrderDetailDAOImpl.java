package lk.ijse.dinemore.dao.custom.impl;

import lk.ijse.dinemore.dao.CrudUtil;
import lk.ijse.dinemore.dao.custom.OrderDetailDAO;
import lk.ijse.dinemore.entity.OrderDetail;

import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean add(OrderDetail od) throws Exception {
        String sql = "Insert into OrderDetail(oid,item,qty) values(?,?,?)";
        return CrudUtil.execute(sql, od.getOrderID(), od.getItem(), od.getQty());
    }

    @Override
    public boolean update(OrderDetail od) throws Exception {
        String sql = "update orderdetail set qty=?,item=? where id=?";
        return CrudUtil.execute(sql, od.getQty(), od.getItem(), od.getId());
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "Delete from orderdetail where id=?";
        return CrudUtil.execute(sql, id);
    }

    @Override
    public OrderDetail search(Integer id) throws Exception {
        OrderDetail order = null;
        try {
            String sql = "Select * from orderdetail where oid=?";
            ResultSet rst = null;
            rst = CrudUtil.execute(sql, id);
            if (rst.next()) {
                order = new OrderDetail(
                        rst.getInt(1),
                        rst.getString(2),
                        rst.getInt(3)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws Exception {
        String sql = "Select * FROM ORDERDETAIL";
        ResultSet rst = CrudUtil.execute(sql);
        ArrayList<OrderDetail> arrayList = new ArrayList<>();
        while (rst.next()) {
            arrayList.add(new OrderDetail(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getInt(4)
            ));
        }
        return arrayList;
    }

    @Override
    public ArrayList<OrderDetail> getAll(int i) throws Exception {
        String sql = "Select * FROM ORDERDETAIL where oid=?";
        ResultSet rst = CrudUtil.execute(sql,i);
        ArrayList<OrderDetail> arrayList = new ArrayList<>();
        while (rst.next()) {
            arrayList.add(new OrderDetail(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getInt(4)
            ));
        }
        return arrayList;
    }
}
