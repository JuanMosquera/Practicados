package co.edu.polinomioCromatico.modelo.grafo;

public class nodoDoble
{
    private nodoDoble ligaIzquierda, ligaDerecha;
    private Object dato;
    
    public nodoDoble(Object d)
    {
        dato = d;
        ligaIzquierda = null;
        ligaDerecha = null;
    }
    
    public void asignaDato(Object d)
    {
        dato = d;
    }
    
    public void asignaLi(nodoDoble li)
    {
        ligaIzquierda = li;
    }
    
    public void asignaLd(nodoDoble ld)
    {
        ligaDerecha = ld;
    }
    
    public Object retornaDato()
    {
        return dato;
    }
    
    public nodoDoble retornaLi()
    {
        return ligaIzquierda;
    }
    
    public nodoDoble retornaLd()
    {
        return ligaDerecha;
    }
}
