package multithread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientAgenda
{
    public static void main(String[] args)
    {
        try
        {
            Scanner entrada = new Scanner(System.in);
            /* instancia o cliente */
            Socket client = new Socket("localhost", 5000);
            ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(client.getInputStream());

            /* solicita o servidor para saber se o contato está na lista */
            System.out.println("Informe a informação que deseja verificar:");
            String solicitacao = entrada.nextLine();
            System.out.println("Enviando mensagem ao servidor.");
            output.writeObject(solicitacao);
            System.out.println("Obtendo resposta do servidor:");
            boolean resposta = (boolean)input.readObject();

            if(resposta)
            {
                System.out.println("O contato está na agenda.");
            }
            else
            {
                System.out.println("O contato não está na agenda");
            }

        }
        catch (Exception e)
        {
            System.out.println("Erro " + e.getMessage());
        }
    }
}
