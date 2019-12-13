package lk.ijse.dinemore.reservation;

import java.util.HashMap;

public class ReservationImpl<T>{

    private HashMap<Object,T> hashMap=new HashMap();

    public boolean reserve(Object key,T service){
        if(hashMap.containsKey(key)){
            return hashMap.get(key)==service;
        }else{
            System.out.println("reserve "+key);
            hashMap.put(key, service);
            return true;
        }
    }

    public boolean release(Object key,T service){
        if(hashMap.containsKey(key)&& (hashMap.get(key)==service)){
            System.out.println("released "+key);
            hashMap.remove(key);
            return true;
        }
        return false;
    }

    public boolean isReserved(Object key){
        if(hashMap.containsKey(key)){
            return true;
        }
        return false;
    }
}
