package co.edu.polinomioCromatico.modelo.grafo;

import co.edu.polinomioCromatico.polinomio.polinomio;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class grafo
{
    private final int vertices;
    private int aristas;
    private final matrizForma2 matrizAdyacencia;
    
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
    
    public void asignaAristas(int a)
    {
        aristas = a;
    }
    
    public int[] recorridoBfs(int v)
    {
        Queue<Integer> cola;
        int visitado[];
        nodoDoble p;
        tripleta tp;
        cola = new LinkedList();
        visitado = new int[vertices+1];
        visitado[v] = 1;
        cola.add(v);
        while (!cola.isEmpty()) 
        {
            v = cola.remove();
            p = matrizAdyacencia.nodoCabeza().retornaLd();
            while(!matrizAdyacencia.finDeRecorrido(p))
            {
                tp = (tripleta)p.retornaDato();
                if(tp.retornaFila()==v)
                {
                    if(visitado[tp.retornaColumna()]==0)
                    {
                        visitado[tp.retornaColumna()]=1;
                        cola.add(tp.retornaColumna());
                    }
                }
                p = p.retornaLd();
            }
        }
        return visitado;
    }
    
    public int obtenerComponentesConexos() 
    {        
        int conexos = 1, j = 0;
        int visitado[] = recorridoBfs(1), aux[];
        for (int i = 0; i < vertices; i++)
        {
            if (visitado[i] == 0) 
            {
                conexos = conexos + 1;
                aux = recorridoBfs(i);
                while (j < vertices)
                {
                    if (aux[j] == 1)
                    {
                        visitado[j] = 1;
                    }
                    j++;
                }
            }
        }
        return conexos;
    }
    
    public grafo construirGrafoConexo(int v) 
    {
        grafo grafoConexo;
        int v1, v2;
        grafoConexo = new grafo(vertices, 0);
        List listaL = new ArrayList();
        List vertic = new ArrayList();
        tripleta arista, tp;
        nodoDoble p = matrizAdyacencia.nodoCabeza().retornaLd();
        Queue<Integer> cola = new LinkedList();
        cola.add(v);
        while (!cola.isEmpty())
        {
            v = cola.remove();
            while(!matrizAdyacencia.finDeRecorrido(p))
            {
                tp = (tripleta)p.retornaDato();
                if(tp.retornaFila()==v)
                {
                    arista = new tripleta(tp.retornaFila(), tp.retornaColumna(), 1);
                    if(!vertic.contains(v))
                    {
                        vertic.add(v);
                    }
                    if(!vertic.contains(tp.retornaColumna()));
                    {
                        vertic.add(tp.retornaColumna());
                    }
                    listaL.add(arista);
                    cola.add(tp.retornaColumna());
                }
                p = p.retornaLd();
            }
        }
        if(!listaL.isEmpty())
        {
            for(int i=0; i<listaL.size();i++)
            {
                v1 = vertic.indexOf(((tripleta)listaL.get(i)).retornaFila())+1;
                v2 = vertic.indexOf(((tripleta)listaL.get(i)).retornaColumna())+1;
                arista = new tripleta(v1, v2, 1);
                p = new nodoDoble(arista);
                grafoConexo.conectaPorFilas(p);
                grafoConexo.conectaPorColumnas(p);
                arista = new tripleta(v2, v1, 1);
                p = new nodoDoble(arista);
                grafoConexo.conectaPorFilas(p);
                grafoConexo.conectaPorColumnas(p);
                grafoConexo.asignaAristas(grafoConexo.retornaAristas()+1);
            }
        }
        return grafoConexo;
    }
    
    public tripleta ciclo() 
    {
        tripleta arista;
        tripleta tp;
        int visitados[];
        nodoDoble p;
        arista = new tripleta(0,0,0);
        visitados = new int[vertices];
        for (int i = 1; i <=vertices; i++) 
        {
            for (int j = i + 1; j <= vertices; j++) 
            {
                p = matrizAdyacencia.retonarNodoEn(i, j);
                if(p.retornaDato() != null)
                {
                    tp = (tripleta)p.retornaDato();
                    if(tp.retornaFila()==i)
                {
                    if(visitados[i]==1 && visitados[tp.retornaColumna()]==1)
                    {
                        arista = tp;
                        return arista;
                    }
                    visitados[i] = 1;
                    visitados[arista.retornaColumna()]=1;
                }
                }
            }
        }
        return arista;
    }
    
    public polinomio crearPolinomioCromatico()
    {
        polinomio pol, pc1, pc2;
        grafo aux;
        pc1 = new polinomio();
        // caso base para un grafo completo
        if ((aristas == ((vertices * (vertices - 1)) / 2) && vertices != 1)) 
        {
            pol = new polinomio("x");
            pc1 = new polinomio("x-1");
            pc1 = pol.multiplicar(pc1);
            for (int i = 2; i < vertices; i++) 
            {
                pol = new polinomio("x" + "-" + String.valueOf(i));
                pc1 = pol.multiplicar(pc1);
            }
            
        } // Caso base para grafo disperso
        else if (aristas == 0)
        {
            pc1 = new polinomio("x" + String.valueOf(vertices));            
        }
        else
        {
            double densidad;
            int vt = ((vertices * (vertices - 1)) / 2);
            densidad = aristas / vt;
            if (densidad < 0.5)
            {
                tripleta aristaCiclo;
                aristaCiclo = ciclo();
                aux = unirVertices(aristaCiclo.retornaFila(),aristaCiclo.retornaColumna());
                eliminarArista(aristaCiclo.retornaFila(), aristaCiclo.retornaColumna());
                pc1 = aux.crearPolinomioCromatico();
                pc2 = crearPolinomioCromatico();
                pol = new polinomio("-1");
                pc2 = pc2.multiplicar(pol);
                pc1 = pc1.suma(pc2);
            }
            else if (densidad >= 0.5) 
            {
                tripleta tf;
                nodoDoble p;
                tf = faltante();
                aux = unirVertices(tf.retornaFila(), tf.retornaColumna());
                p = new nodoDoble(tf);
                matrizAdyacencia.conectaPorFilasForma2(p);
                matrizAdyacencia.conectaPorColumnasForma2(p);
                pc1 = aux.crearPolinomioCromatico();
                pc2 = crearPolinomioCromatico();
                pc1 = pc1.suma(pc2);

            }
        }
        return pc1;
    }
    
    public tripleta faltante() {
        tripleta tf;        
        nodoDoble p,q ; 
        tf = new tripleta(0,0,0);
        p = matrizAdyacencia.nodoCabeza().retornaLd();
        for (int i = 1; i <= vertices; i++) 
        {
            for (int j = i + 1; j <= vertices; j++) 
            {
                q = matrizAdyacencia.retonarNodoEn(i, j);
                if(q.retornaDato() == null)
                {
                    tf = new tripleta(i, j, 1);
                }
            }
        }
        return tf;
    }
    
    public void eliminarArista(int a, int b) 
    {
        nodoDoble p, q;
        tripleta tp;
        p = matrizAdyacencia.nodoCabeza().retornaLd();
        q = new nodoDoble(p);
        tp = (tripleta)p.retornaDato();
        while(tp.retornaFila()!=a&&tp.retornaColumna()!=b)
        {
            q =p;
            tp = (tripleta)p.retornaDato();
            p = p.retornaLd();
        }
        q.asignaLd(p.retornaLd());
        p.retornaLd().asignaLi(q);
        p.asignaLi(null);
        p.asignaLd(null);
        asignaAristas(aristas-1);
    }
    
    public grafo unirVertices(int a, int b) {
        grafo grafoUnido;
        nodoDoble p, q;
        tripleta tp, arista;
        int numeroAristas = 0;
        grafoUnido = new grafo(vertices-1, vertices-1);
        for(int i =1; i<=vertices;i++)
        {
            for (int j = i + 1; j <= vertices; j++) 
            {
                p = matrizAdyacencia.retonarNodoEn(i, j);
                if(p.retornaDato() != null)
                {
                    tp = (tripleta)p.retornaDato();
                if(tp.retornaFila()==i)
                {
                    if(i<b)
                    {
                        if(tp.retornaColumna()<b)
                        {
                            arista = new tripleta(i,tp.retornaColumna(), 1);
                            q = new nodoDoble(arista);
                            grafoUnido.conectaPorFilas(q);
                            grafoUnido.conectaPorColumnas(q);
                            arista = new tripleta(tp.retornaColumna(), i, 1);
                            q = new nodoDoble(arista);
                            grafoUnido.conectaPorFilas(q);
                            grafoUnido.conectaPorColumnas(q);
                            numeroAristas = numeroAristas+1;
                        }
                        else if (tp.retornaColumna() == b)
                        {
                            if (i != a) 
                            {
                                arista = new tripleta(i,a, 1);
                            q = new nodoDoble(arista);
                            grafoUnido.conectaPorFilas(q);
                            grafoUnido.conectaPorColumnas(q);
                            arista = new tripleta(a, i, 1);
                            q = new nodoDoble(arista);
                            grafoUnido.conectaPorFilas(q);
                            grafoUnido.conectaPorColumnas(q);
                            }
                        }
                        else 
                        {
                            arista = new tripleta(i,tp.retornaColumna()-1, 1);
                            q = new nodoDoble(arista);
                            grafoUnido.conectaPorFilas(q);
                            grafoUnido.conectaPorColumnas(q);
                            arista = new tripleta(tp.retornaColumna()-1, i, 1);
                            q = new nodoDoble(arista);
                            grafoUnido.conectaPorFilas(q);
                            grafoUnido.conectaPorColumnas(q);
                            numeroAristas = numeroAristas+1;
                        }
                    }
                    else if (i == b) 
                    {
                        arista = new tripleta(a,tp.retornaColumna()-1, 1);
                            q = new nodoDoble(arista);
                            grafoUnido.conectaPorFilas(q);
                            grafoUnido.conectaPorColumnas(q);
                            arista = new tripleta(tp.retornaColumna()-1, a, 1);
                            q = new nodoDoble(arista);
                            grafoUnido.conectaPorFilas(q);
                            grafoUnido.conectaPorColumnas(q);
                            numeroAristas = numeroAristas+1;
                    } 
                    else 
                    {
                        arista = new tripleta(i-1,tp.retornaColumna()-1, 1);
                            q = new nodoDoble(arista);
                            grafoUnido.conectaPorFilas(q);
                            grafoUnido.conectaPorColumnas(q);
                            arista = new tripleta(tp.retornaColumna()-1, i-1, 1);
                            q = new nodoDoble(arista);
                            grafoUnido.conectaPorFilas(q);
                            grafoUnido.conectaPorColumnas(q);
                            numeroAristas = numeroAristas+1;
                    }
                }
            }
            }
        }
        grafoUnido.asignaAristas(numeroAristas);
        return grafoUnido;
    }
    
    public polinomio polinomioConexo()
    {
        int i, j,  k, numeroDeCompConexa;
        int visitado[], aux[];
        polinomio pc1, p, pc2;
        i = 1;
        j = 1;
        k = 1;
        numeroDeCompConexa = obtenerComponentesConexos();
        visitado = recorridoBfs(1);
        p = new polinomio();
        grafo GC;
        GC = construirGrafoConexo(1);        
        pc1 = GC.crearPolinomioCromatico();
        if (numeroDeCompConexa > 1) 
        {
            while (i < numeroDeCompConexa) 
            {
                if (visitado[j] == 0) 
                {
                    i = i+1;
                    GC = construirGrafoConexo(j);
                    pc2 = GC.crearPolinomioCromatico();
                    pc1 = pc1.multiplicar(pc2);
                    aux = recorridoBfs(j);
                    while (k < vertices)
                    {
                        if (aux[k] == 1)
                        {
                            visitado[k] = 1;
                        }
                        k=k+1;
                    }
                    j=j+1;
                } 
                else
                {
                   i=i+1;
                }
            }
        }
        return pc1;
    }
}
