//package PublicarAssinar;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Grupo_5
 */
public interface ServerInterface extends Remote{
    public String showTopics() throws RemoteException;
    public void register(ClientInterface Ci, int topic, String nome) throws RemoteException;
    public void leave(ClientInterface Ci, int topico) throws RemoteException;
    public void notifyall(int topico, String message) throws RemoteException;
}