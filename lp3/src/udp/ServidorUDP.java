package udp;
import udp.Calculadora;
import java.io.*;
import java.net.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class ServidorUDP
{
    public static void main(String[] args) throws SocketException {
        DatagramSocket server = new DatagramSocket(6200);
        System.out.println("server começou");
        try
        {
            Calculadora calc = new Calculadora();


            while(true)
            {
                byte[] data = new byte[1024];
                DatagramPacket pacote;


                pacote = new DatagramPacket(data, data.length);

                server.receive(pacote);

                String msg = new String(pacote.getData(), 0, pacote.getLength());

                System.out.println("info:" + msg);

                float res = calc.calcula(msg);
                System.out.println("Resultado: "+ res);

                byte[] resData;
                String message = (String) Float.toString(res);
                resData = message.getBytes();
                DatagramPacket pkg = new DatagramPacket(resData, resData.length, pacote.getAddress(), 6200);
                server.send(pkg);


            }

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            if(server!=null && !server.isClosed()){
                server.close();
            }
        }

    }
}
