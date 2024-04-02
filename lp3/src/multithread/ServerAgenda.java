package multithread;

import tcp.Contato;
import tcp.ListaAgenda;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/* TCP */
public class ServerAgenda
{
    public static void main(String[] args)
    {
        try
        {
            /* criando os contatos */
            Contato contato1 = new Contato("tati", "71999362579", "tati@gmail.com");

            Contato contato2 = new Contato("alan", "71912345678", "alan@gmail.com");

            Contato contato3 = new Contato("luiza","71990090101", "luiza@gmail.com");

            ListaAgenda agenda = new ListaAgenda();

            agenda.setListaContatos(contato1);
            agenda.setListaContatos(contato2);
            agenda.setListaContatos(contato3);

            /* instancia o servidor */
            ServerSocket server = new ServerSocket(5000);

            while(true)
            {
                Socket client = server.accept();
                System.out.println("Cliente conectado" + client.getInetAddress().getHostAddress());

                ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(client.getInputStream());

                String solicitacao = (String)input.readObject();

                String s = solicitacao.replaceAll("[()]", "").replaceAll("-", "");
                System.out.println(s);

                boolean verificar = agenda.procuraContato(s);

                output.flush();
                output.writeObject(verificar);

                output.close();
                input.close();

            }
        }
        catch (Exception e)
        {
            System.out.printf("Erro: " + e.getMessage());
        }
    }
}
