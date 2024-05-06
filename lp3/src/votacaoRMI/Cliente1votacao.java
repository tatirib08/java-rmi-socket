package votacaoRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
// import java.util.Arrays;
import java.util.Scanner;
public class Cliente1votacao
{
    public static void main(String[] args)
    {
        try
        {
            String serverAddres = "192.168.0.14"; //desktop daq de casa 
            int port = 1099; //eu
            // String serverAddres = "187.65.151.100"; // alan 
            // int port = 4500; //alan
            Registry registro = LocateRegistry.getRegistry(serverAddres, port);
            Votos stub = (Votos) registro.lookup("Votos");
            
            Scanner entrada = new Scanner(System.in);

            int escolha=0;
            
            do {
                
                System.out.println("Informe o nome do eleitor");
                String nome = entrada.nextLine();
                System.out.println("Informe a senha");
                String senha = entrada.nextLine();

                Usuario eleitor = new Usuario(nome, senha);
                boolean resposta = stub.autoriza(eleitor);

                if(resposta==true)
                {
                    System.out.println("--------VOTAÇÃO PARA PREFEITO------");
                    System.out.println("Candidatos:");
                    System.out.println("1. "+ stub.getNomeCandidato1());
                    System.out.println("2. "+ stub.getNomeCandidato2());
                    System.out.println("Informe o número do candidato que deseja votar:");
                    int op = entrada.nextInt();
                    stub.atualizaPlacar(op);

                    System.out.println("-----RESULTADO------");
                    System.out.println("Candidato 1: "+ stub.getCandidato1() + " votos.");
                    System.out.println("Candidato 2: "+ stub.getCandidato2() + " votos.");

                    int resultado = stub.mostrarResultado();

                    if(resultado == 1)
                    {
                        System.out.println("Candidato 1 está ganhando.");
                    }
                    else if (resultado==2) 
                    {
                        System.out.println("Candidato 2 está ganhando.");
                    }
                    else
                    {
                        System.out.println("Candidatos estão empatados.");
                    }
                }
                else
                {
                    System.out.println("Eleitor não autorizado, informe um cadastro válido.");
                }

                System.out.println("Deseja continuar votando?[aperte -1 para parar]");
                escolha = entrada.nextInt();
                entrada.nextLine(); 
            } while (escolha!=-1);
           
    

            
            entrada.close();
        }
        catch (Exception e)
        {
            System.out.println("Erro " + e.getMessage());
        }
    }
}
