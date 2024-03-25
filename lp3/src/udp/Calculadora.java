package udp;

public class Calculadora
{
    public Calculadora()
    {

    }

    private float soma(float num1, float num2)
    {
        return (num1 + num2);
    }

    private float multiplica(float num1, float num2)
    {
        return (num1 * num2);
    }

    private float divide(float num1, float num2)
    {
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

    private float subtrai(float num1, float num2)
    {
        return (num1 - num2);
    }
    protected float calcula(String solicitacao)
    {
        float resultado = 0;
        String s[] = solicitacao.split(";");
        float num1 = Float.parseFloat(s[0]);
        float num2 = Float.parseFloat(s[1]);
        String op = s[2];

        switch (op)
        {
            case "+":
                resultado = soma(num1, num2);
                break;

            case "-":
                resultado = subtrai(num1, num2);
                break;

            case "*":
                resultado = multiplica(num1, num2);
                break;

            case "/":
                resultado = divide(num1, num2);
                break;
        }

        return resultado;

    }
}
