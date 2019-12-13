package lk.ijse.dinemore.reservation;

import java.rmi.Remote;

public interface Reservation extends Remote{
     boolean reserve(Object key)throws Exception;
     boolean release(Object key)throws Exception;
     boolean isReserved(Object key) throws Exception;
}
