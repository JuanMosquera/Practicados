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
    private polinomio pol,poli2,polisuma;

    
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
    
    public double evaluarP(String a){
        int x =Integer.parseInt(a);
        double result= pol.evaluar(pol, x);
        return result;
    }
    
    public void reiniciarGrafos()
    {
        grafos = new ArrayList<grafo>();
        listaGrafos = new ArrayList<String>(); 
    }
    public void crearPolinomio(String polinomio)
    {
        this.poli2 = new polinomio(polinomio);
               
    }
    
    public String imprimir(){
        String result = poli2.imprimePolinomio();
        return result;
    }
    
    public String suma(){
        pol = pol.suma(poli2);
        return pol.imprimePolinomio();
    }
   
     public String multiplicar(){
        pol = pol.multiplicar(poli2);
        return pol.imprimePolinomio();
    }
     
    public boolean existen(){
    
        return (pol==null || poli2==null);
    }
    
     public String factor(int x){
        String respuesta=pol.esFactor(pol,x);
        return respuesta;
    }
     
    public int extraer(String l){
        
        
        
        l = l.toLowerCase();
        l = l.replace("  ", "");
        l = l.replace(" ", "");
        l = l.replace("+", " +");
        l = l.replace(" -", "-");
        if (l.indexOf("+")!=-1) {
            String[] fracciones;
            fracciones = l.split(" ");
            return Integer.parseInt(fracciones[1]);
        } else {
            
            String[] fracciones = l.split("-");
            return (Integer.parseInt(fracciones[1]));
        }
        
    }

    
}
