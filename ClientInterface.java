//package PublicarAssinar;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author Grupo_5
 */
public interface ClientInterface extends Remote{

    /**
     *
     * @param message
     * @throws RemoteException
     */
    
    public void update(String message) throws RemoteException;
}
