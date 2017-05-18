package practicados;

public class nodoSimple 
{
    private Object dato;
    private nodoSimple liga;
    
    public nodoSimple(Object d)
    {
        dato = d;
        liga = null;
    }
    
    public Object retornaDato()
    {
        return dato;
    }
    
    public nodoSimple retornaLiga()
    {
        return liga;
    }
    
    public void asignaDato(Object d)
    {
        dato = d;
    }
    
    public void asignaLiga(nodoSimple l)
    {
        liga = l;
    }
}
