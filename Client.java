//package PublicarAssinar;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Grupo_5
 */
public class Client implements ClientInterface, Serializable {

    private static final long serialVersionUID = 0;
    private int id;
    private String nome;
    private int topico;

    private Client(int id, String nome) {
        this.setId(id);
        this.setNome(nome);
    }

    public String getNome() {
		return this.nome;
    }

    public void setNome(String nome) {
            this.nome = nome;
    }

    public int getId() {
            return this.id;
    } 

    public void setId(int id) {
            this.id = id;
    }
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String message;
        int option = 0;
        Menu m = new Menu();

        String host = (args.length < 1) ? null : args[0];
        try {

            Registry registry = LocateRegistry.getRegistry(host);
            ServerInterface stub = (ServerInterface) registry.lookup("Publicar");

            Client obj =  criarCliente(stub);
            ClientInterface cliente = (ClientInterface) UnicastRemoteObject.exportObject(obj,0);

            //programa principal
            while(true){
                option = m.MenuPrincipal();
                System.out.println("\n----------------------------------------------------------\n");
                switch (option){
                    case 1:
                        option = m.MenuPublicar();
                        System.out.print("\nDigite a Mensagem: ");
                        message = sc.nextLine();
                        System.out.println("\n----------------------------------------------------------\n");
                        stub.notifyall(option, message);
                        break;
                    case 2:
                        option = m.MenuAssinar();
                        stub.register(cliente, option, obj.nome);
                        System.out.println("\n----------------------------------------------------------\n");
                        break;
		            case 3:
			        option = m.MenuAssinar();
                        stub.leave(cliente, option);
                        System.out.println("\n----------------------------------------------------------\n");
                        break;
                    default:
                        stub.leave(cliente, 1);
                        stub.leave(cliente, 2);
                        stub.leave(cliente, 3);
                        System.exit(1);
                        break;
                }
                
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(String message) throws RemoteException
    {
        System.out.println("Nova notificacao:" + message);
    }

    static public Client criarCliente(ServerInterface stub){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do cliente: ");
        String nome = sc.nextLine();
        System.out.println("Digite o id do cliente: ");
        int id = sc.nextInt();

        Client cliente = new Client (id, nome);
        
        return cliente;
    }
    
}
