package co.edu.practicaGrafos.modelo;

public class polinomio extends listaSimplementeLigada
{
    private int coef;
    private int pot;
    
    //Crea el polinomio constante p(x) = 1
    public polinomio()
    {
        nodoSimple y;
        y = buscaDondeInsertar(0);
        insertar(coef, pot, y);
    }
    
    //Crea el polinomio de la forma P(x) = x+a
    public polinomio(int a)
    {
        nodoSimple y;
        y = buscaDondeInsertar(1);
        insertar(1, 1, y);
        y = buscaDondeInsertar(0);
        insertar(a, 0, y);
    }
    
    //Crea el polinomio de la forma P(x) = a*x^b
    public polinomio(int a, int b)
    {
        nodoSimple y;
        for(int i = 0; i<=b; i++)
        {
            y = buscaDondeInsertar(i);
            if(i==b)
            {
                insertar(a, i, y);
            }
            else
            {
                insertar(0, i, y);
            }
        }
    }
    
    //Retorna el grado del polinomio
    public int grado()
    {
        return primerNodo().retornaPotencia();
    }
    
    
}
