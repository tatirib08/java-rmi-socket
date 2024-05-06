package multithread;

import tcp.Contato;
import tcp.ListaAgenda;

import java.io.InputStream;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HandlerTcp implements Runnable
{
    private final Socket clienteSocket;

    public Socket getClienteSocket()
    {
        return this.clienteSocket;
    }
    public HandlerTcp(Socket socket)
    {
        this.clienteSocket = socket;
    }
    @Override
    public void run()
    {
        try
        {
            /* criando os contatos */
            tcp.Contato contato1 = new tcp.Contato("tati", "71999362579", "tati@gmail.com");

            tcp.Contato contato2 = new tcp.Contato("alan", "71912345678", "alan@gmail.com");

            tcp.Contato contato3 = new Contato("luiza","71990090101", "luiza@gmail.com");

            ListaAgenda agenda = new ListaAgenda();

            agenda.setListaContatos(contato1);
            agenda.setListaContatos(contato2);
            agenda.setListaContatos(contato3);


            ObjectOutputStream output = new ObjectOutputStream(clienteSocket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(clienteSocket.getInputStream());

            String solicitacao = (String)input.readObject();

            String s = solicitacao.replaceAll("[()]", "").replaceAll("-", "");
            System.out.println(s);

            boolean verificar = agenda.procuraContato(s);

//            output.flush();
            output.writeObject(verificar);

            output.close();
            input.close();

            /* teste */
//            agenda.printLista();
//            System.out.println("mensagem do cliente:"+solicitacao);
            System.out.println("Thread: ");
            System.out.println(Thread.currentThread().getName());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
