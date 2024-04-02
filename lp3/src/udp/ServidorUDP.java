package udp;
import udp.Calculadora;
import java.io.*;
import java.net.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class ServidorUDP
{
    public static void main(String[] args) throws SocketException {

        try
        {
            DatagramSocket server = new DatagramSocket(6200);
            System.out.println("server começou");
            Calculadora calc = new Calculadora();
            byte[] data = new byte[1024];
            byte[] resData = new byte[1024];
            while(true)
            {

                DatagramPacket pacote = new DatagramPacket(data, data.length);

                server.receive(pacote);

                String msg = new String(pacote.getData(), 0, pacote.getLength());

                System.out.println("info:" + msg);

                float res = calc.calcula(msg);
                System.out.println("Resultado: "+ res);


                InetAddress address = pacote.getAddress();
                String message = String.valueOf(res);
                resData = message.getBytes();
                System.out.println("PORTA -> " + pacote.getPort());
                DatagramPacket pkg = new DatagramPacket(resData, resData.length, pacote.getAddress(), pacote.getPort());
                server.send(pkg);
                System.out.println("AQUIIIIIIIIIIII");

            }

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }


    }
}
