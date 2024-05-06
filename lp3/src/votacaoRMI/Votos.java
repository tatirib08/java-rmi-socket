package votacaoRMI;
import java.rmi.*;
// import java.util.concurrent.atomic.AtomicInteger;

public interface Votos extends Remote
{

    public boolean autoriza(Usuario eleitor) throws RemoteException;

    public void atualizaPlacar(int candidato) throws RemoteException;

    public int mostrarResultado() throws RemoteException;

    public String getNomeCandidato1() throws RemoteException;

    public String getNomeCandidato2() throws RemoteException;

    public int getCandidato1() throws RemoteException;

    public int getCandidato2() throws RemoteException;
}
