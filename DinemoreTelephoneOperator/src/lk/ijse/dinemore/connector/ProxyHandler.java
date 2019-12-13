package lk.ijse.dinemore.connector;


import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.custom.TeleOperatorService;

import java.rmi.Naming;

public class ProxyHandler {
    private static ProxyHandler proxyHandler;
    private TeleOperatorService teleOperatorService;

    private ProxyHandler() throws Exception{
        ServiceFactory lookup = (ServiceFactory) Naming.lookup("rmi://localhost:5050/DinomoreServer");
        teleOperatorService= (TeleOperatorService) lookup.getService(ServiceFactory.ServiceTypes.TELEOPERATOR);
    }

    public static ProxyHandler getInstance(){
        if(null==proxyHandler){
            try {
                proxyHandler=new ProxyHandler();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return proxyHandler;
    }

    public TeleOperatorService getTeleOperatorService() {
        return teleOperatorService;
    }
}
