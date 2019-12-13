package lk.ijse.dinemore.dao;

import lk.ijse.dinemore.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public enum DAOTypes{
        ORDER,CUSTOMER,ORDERDETAIL,EMPLOYEE;
    }

    public static DAOFactory getInstance(){
        if(null==daoFactory){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes type){
        switch (type){
            case ORDER:return new OrderDAOImpl();
            case CUSTOMER:return new CustomerDAOImpl();
            case ORDERDETAIL:return new OrderDetailDAOImpl();
            case EMPLOYEE:return new EmployeeDAOImpl();
            default:return null;
        }
    }
}
