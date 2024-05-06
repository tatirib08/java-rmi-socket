package votacao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Scanner;
import votacao.Votos;

public class Client2
{
    public static void main(String[] args) throws SocketException
    {
        try
        {
            Scanner entrada = new Scanner(System.in);
            Votos votacao = new Votos();

            /* instancia o cliente */
            Socket client = new Socket("localhost", 5000);
            ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(client.getInputStream());

            int voto =0;
            do
            {
                votacao.menu();
                System.out.println("Informe um voto: (-1 para terminar)");
                voto= entrada.nextInt() ;
                System.out.println("Enviando mensagem ao servidor.");
                output.writeObject(voto);
                output.flush();
                System.out.println("Obtendo resposta do servidor:");
                int ganhador = (int)input.readObject();
                if (ganhador == 0)
                {
                    System.out.println("Estão empatados.");
                }
                else
                {
                    System.out.println("OP " + ganhador + "está vencendo a votação.");
                }

            } while (voto!=-1);
            output.close();
            client.close();
        }
        catch (Exception e)
        {
            System.out.println("Erro " + e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
