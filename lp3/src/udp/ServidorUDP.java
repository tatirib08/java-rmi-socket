package udp;
import udp.Calculadora;
import java.io.*;
import java.net.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class ServidorUDP
{
    public static void main(String[] args)
    {
        try
        {
            Calculadora calc = new Calculadora();
            DatagramSocket server = new DatagramSocket(6200);
            System.out.println("server começou");

            byte[] data = new byte[1024];
            DatagramPacket pacote;

            pacote = new DatagramPacket(data, data.length);

            server.receive(pacote);

            String msg = new String(pacote.getData(), 0, pacote.getLength());

            System.out.println("info:" + msg);

            float res = calc.calcula(msg);
            System.out.println("Resultado: "+ res);

            byte[] resData;
            String message = String.valueOf(res);
            resData = message.getBytes();
            DatagramPacket pkg = new DatagramPacket(resData, resData.length, 6200);
            server.send(pkg);

            server.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }


    }
}
