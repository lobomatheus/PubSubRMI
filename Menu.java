import java.util.Scanner;

/**
 *
 * @author Grupo_5
 */

public class Menu {
    private Scanner s = new Scanner(System.in);

    public Menu(){}

    public int MenuPrincipal(){
        System.out.println("1.Publicar\n2.Assinar\n3.Desassinar\n4.Sair");
        return s.nextInt();
    }

    public int MenuPublicar(){
        System.out.println("Publicar em:\n1.Esportes\n2.Cultura\n3.Educacao");
        return s.nextInt();
    }

    public int MenuAssinar(){
        System.out.println("Escolha:\n1.Esportes\n2.Cultura\n3.Educacao");
        return s.nextInt();
    }
}