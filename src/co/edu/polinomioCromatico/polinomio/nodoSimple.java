package co.edu.polinomioCromatico.polinomio;

public class nodoSimple 
{
    private Object dato;
    private int potencia;
    private nodoSimple liga;
    
    public nodoSimple(Object d, int coef)
    {
        dato = d;
        potencia  = coef;
        liga = null;
    }
    
    public Object retornaDato()
    {
        return dato;
    }
    
    public int retornaPotencia()
    {
        return potencia;
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
    
    public void asignaCoeficiente(int c)
    {
        potencia = c;
    }
}