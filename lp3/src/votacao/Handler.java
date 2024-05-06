package votacao;
import java.io.InputStream;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class Handler implements Runnable
{
    private Socket clienteSocket;
    private Votos votacao = new Votos();
    public Socket getClienteSocket()
    {
        return this.clienteSocket;
    }
    public Handler(Socket socket, Votos votacao)
    {
        this.clienteSocket = socket;
        this.votacao = votacao;

    }
    public void setClienteSocket(Socket cliente)
    {
        this.clienteSocket = cliente;
    }

    @Override
    public void run()
    {
        try
        {
            ObjectOutputStream output = new ObjectOutputStream(clienteSocket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(clienteSocket.getInputStream());

            int voto = 0;

            do{
                voto = (int)input.readObject();
                votacao.atualizarPlacar(voto);
                int resultado = votacao.mostrarResultado();

                System.out.println("\nResultado: " + resultado);

                output.writeObject(resultado);
                output.flush();
            }while (voto != -1);

            output.close();
            input.close();

            System.out.print("Thread: ");
            System.out.println(Thread.currentThread().getName());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
