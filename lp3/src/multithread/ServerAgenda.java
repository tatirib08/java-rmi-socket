package multithread;

import tcp.Contato;
import tcp.ListaAgenda;
import multithread.HandlerTcp;
import multithread.ClientAgenda;
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
            /* instancia o servidor */
            ServerSocket server = new ServerSocket(5000);

            while(true)
            {

                Socket client = server.accept();
                System.out.println("Cliente conectado" + client.getInetAddress().getHostAddress());

                /* create a handler for clients */
                HandlerTcp clientHandler = new HandlerTcp(client);

                Thread thread = new Thread(clientHandler);
                thread.start();

            }
        }
        catch (Exception e)
        {
            System.out.printf("Erro: " + e.getMessage());
        }
    }
}
