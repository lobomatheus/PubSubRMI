//package PublicarAssinar;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Grupo_5
 */
public class Server implements ServerInterface {

    HashMap<Integer, ArrayList<ClientInterface>> clientes = new HashMap<Integer, ArrayList<ClientInterface>>();
    
    public Server() {
        clientes.put(1, new ArrayList<ClientInterface>());
        clientes.put(2, new ArrayList<ClientInterface>());
        clientes.put(3, new ArrayList<ClientInterface>());
    };

    public static void main(String[] args) throws InterruptedException {
        try {
            Server obj = new Server();
            ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Publicar", stub);

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }       
    }

    @Override
    public String showTopics() throws RemoteException {
        return "1.Esportes\n2.Cultura\n3.Educacao\n4.Sair";
    }
    
    @Override 
    public void register(ClientInterface Ci, int topic, String nome) throws RemoteException
    {
        clientes.get(topic).add(Ci);
        try{
            System.out.println("Cliente adicionado: " + nome + " - Topico: " + topic);
        }
        catch (Exception e){}
    }
    
  
    public void leave(ClientInterface Ci, int topico) throws RemoteException
    {
        clientes.get(topico).remove(Ci);
    }
    
    public void notifyall(int topico, String message) throws RemoteException
    {
        int i;
        for(i=0;i<clientes.get(topico).size();i++)
        {
            try{
                clientes.get(topico).get(i).update(message);
            }
            catch (RemoteException E){}
        }
    }

}