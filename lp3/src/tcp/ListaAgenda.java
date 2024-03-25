package tcp;

import tcp.Contato;

import java.util.ArrayList;

public class ListaAgenda
{
    private final ArrayList<Contato> listaContatos = new ArrayList<Contato>();

    public ListaAgenda()
    {

    }

    public ArrayList<Contato> getListaContatos()
    {
        return listaContatos;
    }

    public void setListaContatos(Contato contato)
    {
        /* adiciona contatos na lista */
        this.listaContatos.add(contato);
    }

    /* imprime a agenda */
    public void printLista()
    {
        for (Contato i : this.listaContatos)
        {
            System.out.println(i.getNome());
        }
    }

    /* busca contato na lista */
    public boolean procuraContato(String contatoSolicitado)
    {
        System.out.println("Buscando contato na agenda.");
        boolean estaNaLista = false;

        for (int i=0; i< this.listaContatos.size(); i++)
        {

            if(this.listaContatos.get(i).getNome().equalsIgnoreCase(contatoSolicitado))
            {
                estaNaLista = true;
                break;
            } else if (this.listaContatos.get(i).getEmail().equalsIgnoreCase(contatoSolicitado))
            {
                estaNaLista =true;
                break;
            }
            else if (this.listaContatos.get(i).getTelefone().equals(contatoSolicitado))
                {
                    estaNaLista = true;
                    break;
                }



        }
        return estaNaLista;
    }
}
