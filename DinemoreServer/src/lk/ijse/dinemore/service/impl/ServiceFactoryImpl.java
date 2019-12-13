package lk.ijse.dinemore.service.impl;

import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.SuperService;
import lk.ijse.dinemore.service.custom.impl.ChefServiceImpl;
import lk.ijse.dinemore.service.custom.impl.ManagerServiceImpl;
import lk.ijse.dinemore.service.custom.impl.TeleOperatorServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory {

    private static ServiceFactory serviceFactory;

    private ServiceFactoryImpl() throws RemoteException {}

    public static ServiceFactory getInstance() throws Exception {
        if(null==serviceFactory){
            serviceFactory=new ServiceFactoryImpl();
        }
        return serviceFactory;
    }

    @Override
    public SuperService getService(ServiceTypes serviceTypes) throws Exception {
        switch (serviceTypes){
            case CHEF:return new ChefServiceImpl();
            case TELEOPERATOR:return new TeleOperatorServiceImpl();
            case MANAGER: return new ManagerServiceImpl();
            default: return null;
        }
    }
}
