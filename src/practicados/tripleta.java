package practicados;

public class tripleta 
{
    private int fila, columna;
    private Object valor;
    
    //Constructor de la clase tirpleta
    public tripleta(int f, int c, Object v)
    {
        fila = f;
        columna = c;
        valor = v;
    }
    
    public void asignaFila(int f)
    {
        fila = f;
    }
    
    public void asignaColumna(int c)
    {
        columna = c;
    }
    
    public void asignaValor(Object v)
    {
        valor = v;
    }
    
    public int retornaFila()
    {
        return fila;
    }
    
    public int retornaColumna()
    {
        return columna;
    }
    
    public Object retornaValor()
    {
        return valor;
    }
}
