package co.edu.polinomioCromatico.modelo.grafo;

public class grafo
{
    private int vertices;
    private int aristas;
    private matrizForma2 matrizAdyacencia;
    
    public grafo(int v, int a)
    {
        vertices = v;
        aristas = a;
        matrizAdyacencia = new matrizForma2(vertices, vertices);        
    }
    
    public void conectaPorFilas(nodoDoble x)
    {
        matrizAdyacencia.conectaPorFilasForma2(x);
    }
    
    public void conectaPorColumnas(nodoDoble x)
    {
        matrizAdyacencia.conectaPorColumnasForma2(x);
    }
    
    public void muestraMatriz()
    {
        matrizAdyacencia.muestraMatriz();
    }
    
    public int retornaVertices()
    {
        return vertices;
    }
    
    public int retornaAristas()
    {
        return aristas;
    }
}
