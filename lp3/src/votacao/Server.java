package votacao;

import votacao.Handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

public class Server
{
    public static void main(String[] args) throws SocketException
    {
        try
        {
            /* instancia o servidor */
            ServerSocket server = new ServerSocket(5000);
            Votos votacao = new Votos();
            while(true)
            {

                Socket client = server.accept();
                System.out.println("Cliente conectado" + client.getInetAddress().getHostAddress());

                /* create a handler for clients */

                Handler clientHandler = new Handler(client, votacao);

                Thread thread = new Thread(clientHandler);
                thread.start();
//                thread.join();



            }
        }
        catch (Exception e)
        {
            System.out.printf("Erro: " + e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
