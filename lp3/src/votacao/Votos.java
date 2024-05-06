package votacao;
import java.util.concurrent.atomic.AtomicInteger;
public class Votos
{
    public Votos()
    {

    }

    private AtomicInteger op1 = new AtomicInteger(0);
    private AtomicInteger op2 = new AtomicInteger(0);

    public int getOp1()
    {
        return this.op1.get();
    }

    public int getOp2()
    {
        return this.op2.get();
    }

    public void atualizarPlacar(int op)
    {
        switch (op)
        {
            case 1:
                this.op1.incrementAndGet();
                break;

            case 2:
                this.op2.incrementAndGet();
                break;
        }
    }

    public int mostrarResultado()
    {
        System.out.println("Opção 1: "+ getOp1() + " votos");

        System.out.println("Opção 2: "+ getOp2() + " votos");

        if (getOp1() > getOp2())
        {
            return 1;
        }
        else if (getOp2() > getOp1())
        {
            return 2;
        }
        else
        {
            return 0;
        }
    }

    public void menu()
    {
        System.out.println("-----VOTAÇÃO-----");
        System.out.println("Qual é o melhor irmão Salvatore de The Vampire Diares?");
        System.out.println("1. Stefan Salvatore.");
        System.out.println("2. Damon Salvatore.");

    }
}
