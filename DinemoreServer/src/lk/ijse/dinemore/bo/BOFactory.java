package lk.ijse.dinemore.bo;

import lk.ijse.dinemore.bo.custom.impl.CustomerBOImpl;
import lk.ijse.dinemore.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.dinemore.bo.custom.impl.OrderDetailBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public enum BOTypes{
        ORDER,CUSTOMER,EMPLOYEE;
    }

    public static BOFactory getInstance(){
        if(null==boFactory){
            boFactory=new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case ORDER:return new OrderDetailBOImpl();
            case CUSTOMER:return  new CustomerBOImpl();
            case EMPLOYEE:return new EmployeeBOImpl();
            default:return null;
        }
    }
}
