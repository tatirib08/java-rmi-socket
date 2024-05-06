package rmi;
import java.rmi.*;

/* interface remota */
public interface Calculadora extends Remote
{
    double soma(double num1, double num2) throws RemoteException;

    double multiplica(double num1, double num2) throws RemoteException;

    double divide(double num1, double num2) throws RemoteException;

    double subtrai(double num1, double num2) throws RemoteException;

    double raizQuadrada(double num) throws RemoteException;

    double potencia(double num, int expoente) throws RemoteException;
}
