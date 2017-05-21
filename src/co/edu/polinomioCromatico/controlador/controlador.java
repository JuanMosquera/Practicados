package co.edu.polinomioCromatico.controlador;

import co.edu.polinomioCromatico.AdministradorArchivos.administradorArchivos;
import co.edu.polinomioCromatico.modelo.grafo.grafo;
import co.edu.polinomioCromatico.polinomio.polinomio;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Mario
 */
public class controlador 
{
    private administradorArchivos adm;
    private ArrayList<grafo> grafos;
    private ArrayList<String> listaGrafos;
    private polinomio pol;

    
    public controlador()
    {
        adm = new administradorArchivos();
        grafos = new ArrayList<grafo>();
        listaGrafos = new ArrayList<String>();
    }
    
    public void leerArchivo(String direccion)
    {
        try
        {
            grafos = adm.leerArchivo(direccion);
        }catch(FileNotFoundException e){}
        for(int i = 0; i<grafos.size();i++)
        {
            String grafo = "G("+grafos.get(i).retornaVertices()+","+grafos.get(i).retornaAristas()+")";
            listaGrafos.add(grafo);
        }
    }
    
    public String obtenerGrafoEnPosicion(int i)
    {
        String grafo = "G("+grafos.get(i).retornaVertices()+","+grafos.get(i).retornaAristas()+")";
        return grafo;
    }
    
    public int obtenerLongitudArray()
    {
        return grafos.size();
    }
    
    public String calcularPolinomio(int i)
    {
        grafo grafoElegido = grafos.get(i-1);
        pol = grafoElegido.polinomioConexo();
        return pol.imprimePolinomio();
    }
    
    public void reiniciarGrafos()
    {
        grafos = new ArrayList<grafo>();
        listaGrafos = new ArrayList<String>(); 
    }
    public void crearPolinomio2(String polinomio)
    {
//        this.polinomio2 = new Polinomio(polinomio);
//        this.polinomio2 = polinomio2.ordernar();
//        this.polinomio2 = polinomio2.minimizar();        
    }
    
}
