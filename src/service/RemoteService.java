package service;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteService extends Remote {
  byte[] doOperation(RemoteObjectRef o, int methodId, byte[] arguments) throws RemoteException, IOException;
}
