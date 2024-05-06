package rmi;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class ClientRMI
{
    public static void main(String[] args)
    {
        try
        {
            /* localizar o registro */
            Registry registry = LocateRegistry.getRegistry("localhost");

            Calculadora stub = (Calculadora) registry.lookup("Calculadora");
            double a = 10;
            double b = 2;

            System.out.println("soma de "+a + " + "+ b +":" + stub.soma(a,b));
            System.out.println("subtração de "+a + "-"+ b +":" + stub.subtrai(a,b));
            System.out.println("multiplicação de "+a + " * "+ b +":" + stub.multiplica(a,b));
            System.out.println("divisão de "+a + " / "+ b +":" + stub.divide(a,b));
            System.out.println("raiz quadrada de "+a+" : "+ stub.raizQuadrada(a));
            System.out.println("potência de "+a+"elevado a "+b+" : "+stub.potencia(a,(int)b));
        }
        catch (Exception e)
        {
            System.out.println("erro:" + Arrays.toString(e.getStackTrace()));
        }
    }
}
