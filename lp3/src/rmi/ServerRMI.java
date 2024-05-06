package rmi;

import java.rmi.RemoteException;
import java.lang.Math;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class ServerRMI implements Calculadora
{

    @Override
    public double soma(double num1, double num2) throws RemoteException {
        return num1+num2;
    }

    @Override
    public double multiplica(double num1, double num2) throws RemoteException {
        return num1*num2;
    }

    @Override
    public double divide(double num1, double num2) throws RemoteException {
        if(num2 == 0)
        {
            System.out.println("Operação inválida");
            return num1;
        }
        else
        {
            return (num1/num2);
        }
    }

    @Override
    public double subtrai(double num1, double num2) throws RemoteException {
        return num1-num2;
    }

    @Override
    public double raizQuadrada(double num) throws RemoteException
    {
       return Math.sqrt(num);
    }

    @Override
    public double potencia(double num, int expoente) throws RemoteException
    {
        return Math.pow(num, expoente);
    }

    public static void main(String[] args)
    {
        try
        {
            ServerRMI server = new ServerRMI();
            Calculadora stub = (Calculadora) UnicastRemoteObject.exportObject(server, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Calculadora", stub);

            System.out.println("servidor rodando");
        }
        catch (Exception e)
        {
            System.out.println("erro: "+ Arrays.toString(e.getStackTrace()));
        }
    }
}
