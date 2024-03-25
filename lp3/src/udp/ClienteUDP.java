package udp;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteUDP
{
    public static void main(String[] args) throws IOException
    {
        /* colocar a verificação dos args */

        try
        {
            DatagramPacket pacote;
            byte[] data;

            DatagramSocket client = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            Scanner entrada = new Scanner(System.in);

            System.out.println("Informe o primeiro número a ser operado:");
            String num1 = entrada.nextLine();
            System.out.println("Informe o segundo número a ser operado:");
            String num2 = entrada.nextLine();
            System.out.println("Informe a operação a ser realizada: +,-,*,/");
            String op = entrada.nextLine();

            String solicitacao = num1 + ";" + num2 + ";" + op;
            data  = solicitacao.getBytes();
            pacote = new DatagramPacket(data, data.length, address, 6200);
            client.send(pacote);

            client.receive(pacote);
            String resultado = new String(pacote.getData(), 0, pacote.getLength());
            System.out.println(resultado);
            client.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
