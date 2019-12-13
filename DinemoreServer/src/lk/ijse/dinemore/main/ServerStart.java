package lk.ijse.dinemore.main;

import lk.ijse.dinemore.service.impl.ServiceFactoryImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerStart {
    public static void main(String[] args) {
        try {
            Registry registry= LocateRegistry.createRegistry(5050);
            registry.rebind("DinomoreServer", ServiceFactoryImpl.getInstance());
            System.out.println("Server Start...");
        } catch (Exception e) {
            System.out.println("Server Start excep");
            e.printStackTrace();
        }
    }
}
