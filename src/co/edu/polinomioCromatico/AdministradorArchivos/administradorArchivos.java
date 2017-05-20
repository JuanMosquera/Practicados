package co.edu.polinomioCromatico.AdministradorArchivos;

import co.edu.polinomioCromatico.modelo.grafo.grafo;
import co.edu.polinomioCromatico.modelo.grafo.nodoDoble;
import co.edu.polinomioCromatico.modelo.grafo.tripleta;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class administradorArchivos {
    
    private ArrayList<grafo> grafos;
    private grafo grafoLeido;
    
    public administradorArchivos(){}
    
    public ArrayList leerArchivo(String direccion) throws FileNotFoundException
    {
        String linea;
        int v = 0;
        int a = 0;
        boolean esNumero = false;
        String numero = "";
        BufferedReader br = new BufferedReader(new FileReader(direccion));
        linea = "";
        grafos = new ArrayList<grafo>();
        try{
            linea  = br.readLine();
            while(linea!=null)
            {
                if(linea.charAt(0)=='p')
                {
                    for(int i=1; i<linea.length();i++)
                    {
                        try
                        {
                            Integer.parseInt(String.valueOf(linea.charAt(i)));
                            esNumero = true;
                        }catch(NumberFormatException e)
                        {
                            esNumero = false;
                        }
                        if(esNumero==true)
                        {
                          numero = numero+linea.charAt(i);  
                        }
                        if(!"".equals(numero)&&i+1<linea.length())
                        {
                            for(int j=i+1; /*(linea.charAt(j)!=' ')||*/(j<linea.length()); j++)
                            {
                                if(linea.charAt(j)==' ')
                                {
                                    break;
                                }
                                numero = numero+linea.charAt(j);
                            }
                            if(v!=0)
                            {
                                a = Integer.parseInt(numero);
                                numero = "";
                            }
                            else
                            {
                                v = Integer.parseInt(numero);
                                numero = "";
                            }
                        }
                    }                    
                    System.out.println(v+""+a);
                    numero = "";
                    grafoLeido = new grafo(v, a);
                    grafos.add(grafoLeido);
                    int aristas = a;                    
                    for(int i=1; i<=aristas; i++)
                    {
                        try
                        {
                            linea  = br.readLine();
                            if(linea.charAt(0)=='e')
                            {
                                v = 0;
                                for(int j=1; j<linea.length();j++)
                                {
                                    try
                                    {
                                        Integer.parseInt(String.valueOf(linea.charAt(j)));
                                        esNumero = true;
                                    }catch(NumberFormatException e)
                                    {
                                        esNumero = false;
                                    }
                                    if(esNumero==true)
                                    {
                                        numero = numero+linea.charAt(j);
                                    }
                                    if(!"".equals(numero))
                                    {
                                        for(int k=j+1; /*(linea.charAt(k)!=' ')||*/(k<linea.length()); k++)
                                        {
                                            if(linea.charAt(k)==' ')
                                            {
                                                break;
                                            } 
                                            numero = linea.charAt(j)+""+linea.charAt(k);
                                            System.out.println(numero);
                                        }
                                        System.out.println(numero);
                                        if(v!=0)
                                        {
                                            System.out.println(numero);
                                            a = Integer.parseInt(numero);
                                            numero = "";
                                        }
                                        else
                                        {
                                            v = Integer.parseInt(numero);
                                            numero = "";
                                        }                                                                                
                                    }                                    
                                }
                            }
                            System.out.println(v+" "+a);
                            tripleta t = new tripleta(v, a, 1);
                            nodoDoble x = new nodoDoble(t);
                            System.out.println(x);
                            grafoLeido.conectaPorFilas(x);
                            grafoLeido.conectaPorColumnas(x);
                            numero = "";
                        }catch(IOException ex){}
                    }
                }
                linea = br.readLine();
            }          
        }catch(IOException ex){}
        return grafos;
    }
}
