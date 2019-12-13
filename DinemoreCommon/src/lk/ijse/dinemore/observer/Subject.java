package lk.ijse.dinemore.observer;

import java.rmi.Remote;

public interface Subject extends Remote {
    public void register(Observer ob) throws Exception;
    public void unRegister(Observer ob) throws Exception;
    public void notifyObservers() throws Exception;
}
