package lk.ijse.dinemore.observer;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ObserverImpl extends UnicastRemoteObject implements Subject {
    private static ArrayList<Observer> observers = new ArrayList<>();

    public ObserverImpl() throws Exception {
    }

    @Override
    public void register(Observer observer) throws Exception {
        observers.add(observer);
    }

    @Override
    public void unRegister(Observer observer) throws Exception {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
//        for (Observer o :observers) {
//            new Thread(() -> {
//                try {
//                    o.update();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
    }
}
