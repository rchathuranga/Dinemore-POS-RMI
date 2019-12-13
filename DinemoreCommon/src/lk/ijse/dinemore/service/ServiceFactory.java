package lk.ijse.dinemore.service;

import java.rmi.Remote;

public interface ServiceFactory extends Remote {
    enum ServiceTypes{
        TELEOPERATOR,CHEF,MANAGER;
    }

    SuperService getService(ServiceTypes types) throws Exception;
}
