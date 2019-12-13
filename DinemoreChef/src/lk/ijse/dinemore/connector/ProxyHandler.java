package lk.ijse.dinemore.connector;


import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.custom.ChefService;

import java.rmi.Naming;

public class ProxyHandler {
    private static ProxyHandler proxyHandler;
    private ChefService service;

    private ProxyHandler() throws Exception{
        ServiceFactory lookup = (ServiceFactory) Naming.lookup("rmi://localhost:5050/DinomoreServer");
        service= (ChefService) lookup.getService(ServiceFactory.ServiceTypes.CHEF);
    }

    public static ProxyHandler getInstance() throws Exception {
        if(null==proxyHandler){
            proxyHandler=new ProxyHandler();
        }
        return proxyHandler;
    }

    public ChefService getService() {
        return service;
    }
}
