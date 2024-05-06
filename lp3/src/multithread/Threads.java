package multithread;


public class Threads
{
    public static void main(String[] args)
    {

        MeuRunnable r = new MeuRunnable();
        Thread t1 = new Thread(r);
        t1.start();
        Thread t2 = new Thread(r);
        t2.start();
        Thread t3 = new Thread(r);
        t3.start();
        Thread t4 = new Thread(r);
        t4.start();
        Thread t5 = new Thread(r);
        t5.start();


    }
}