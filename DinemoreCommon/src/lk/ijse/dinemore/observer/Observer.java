package lk.ijse.dinemore.observer;

import java.rmi.Remote;

public interface Observer extends Remote {
    public void update() throws Exception;
}
