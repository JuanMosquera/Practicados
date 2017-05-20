package co.edu.polinomioCromatico.controlador;

import co.edu.polinomioCromatico.AdministradorArchivos.administradorArchivos;
import co.edu.polinomioCromatico.modelo.grafo.grafo;
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
    
    public controlador()
    {
        adm = new administradorArchivos();
        grafos = new ArrayList<grafo>();
    }
    
    public void leerArchivo(String direccion)
    {
        try
        {
            grafos = adm.leerArchivo(direccion);
        }catch(FileNotFoundException e){}
    }
    
}
