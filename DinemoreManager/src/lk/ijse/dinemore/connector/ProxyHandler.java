package lk.ijse.dinemore.connector;

import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.custom.ManagerService;

import java.rmi.Naming;

public class    ProxyHandler {
    private static ProxyHandler proxyHandler;
    private ManagerService managerService;

    private ProxyHandler() throws Exception{
        ServiceFactory lookup = (ServiceFactory) Naming.lookup("rmi://localhost:5050/DinomoreServer");
        managerService= (ManagerService) lookup.getService(ServiceFactory.ServiceTypes.MANAGER);
    }

    public static ProxyHandler getInstance() throws Exception {
        if(null==proxyHandler){
            proxyHandler=new ProxyHandler();
        }
        return proxyHandler;
    }

    public ManagerService getService(){
        return managerService;
    }
}
