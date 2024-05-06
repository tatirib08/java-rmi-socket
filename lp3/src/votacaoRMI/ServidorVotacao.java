package votacaoRMI;
// import votacaoRMI.*;

// import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
// import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ServidorVotacao implements Votos
{

    private AtomicInteger candidato1 = new AtomicInteger(0);
    private AtomicInteger candidato2 = new AtomicInteger(0);
    private String nomeCand1 = "Damon";
    private String nomeCand2 = "Stefan"; 

    private ArrayList<Usuario> listaEleitores = new ArrayList<Usuario>();

    @Override
    public boolean autoriza(Usuario eleitor) throws RemoteException
    {
        
        // boolean resposta=false;
        System.out.println("ENTROU EM AUTORIZA ");
        for(Usuario user : this.listaEleitores)
        {
            if ((user.getNome().equalsIgnoreCase(eleitor.getNome())) && (user.getSenha().equals(eleitor.getSenha())))
            {
                System.out.println("eleitor autorizado");
                // resposta = true;
                return true; 
            }
        }
        
        System.out.println("eleitor não autorizado");
        // resposta = false;
        return false; 
    
        // return resposta;
    }

    @Override
    public void atualizaPlacar(int candidato) throws RemoteException
    {
        switch (candidato)
        {
            case 1:
                // this.op1.incrementAndGet();
                this.candidato1.incrementAndGet();
                break;

            case 2:
                // this.op2.incrementAndGet();
                this.candidato2.incrementAndGet(); 
                break;
        }
    }

    @Override
    public int mostrarResultado() throws RemoteException
    {
        
        if (getCandidato1() > getCandidato2())
        {
            return 1; //1 ta ganhando
        }
        else if (getCandidato2() > getCandidato2())
        {
            return 2; // 2 ta ganhando
        }
        else
        {
            return 0; //estão empatados
        }
    }

    @Override
    public int getCandidato1() {
        return this.candidato1.get();
    }

    public void setCandidato1(AtomicInteger candidato1) {
        this.candidato1 = candidato1;
    }

    @Override
    public String getNomeCandidato1()
    {
        return this.nomeCand1;
    }

    @Override
    public int getCandidato2() {
        return this.candidato2.get();
    }

    @Override
    public String getNomeCandidato2()
    {
        return this.nomeCand2; 
    }

    public void setCandidato2(AtomicInteger candidato2) {
        this.candidato2 = candidato2;
    }

    public ArrayList<Usuario> getListaEleitores() {
        return this.listaEleitores;
    }

    public void setListaEleitores(Usuario eleitor) {
        this.listaEleitores.add(eleitor);
    }

    public void printListaEleitores()
    {
        for(Usuario user : this.listaEleitores)
        {
            System.out.println();
            System.out.println("Usuário " + this.listaEleitores.indexOf(user));
            System.out.println("Login: " + user.getNome());
            System.out.println("Senha: " + user.getSenha());
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        try
        {
            String hostname = "192.168.0.13"; //meu notebook em casa
            

            System.setProperty("java.rmi.server.hostname", hostname);

            ServidorVotacao servidor = new ServidorVotacao();
            Votos stub = (Votos) UnicastRemoteObject.exportObject(servidor, 0);
            Registry registro = LocateRegistry.createRegistry(1099);
            registro.bind("Votos", stub);

            System.out.println("servidor rodando");
            /* cria arrylist de usuarios */
            Usuario eleitor1 = new Usuario("tati", "1234");
            Usuario eleitor2 = new Usuario("alan", "5678");
            Usuario eleitor3 = new Usuario("luiza", "9090");
            servidor.setListaEleitores(eleitor1);
            servidor.setListaEleitores(eleitor2);
            servidor.setListaEleitores(eleitor3);

            System.out.println("Eleitores cadastrados.");

            servidor.printListaEleitores();
        }
        catch (Exception e)
        {
            System.out.println("Erro: " +e.getMessage());
        }
    }


}
