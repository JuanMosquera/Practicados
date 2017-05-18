
package co.edu.practicaGrafos.modelo;

public class listaSimplementeLigada 
{
    private nodoSimple primero, ultimo;
    
    //Constructor de la clase Lista Simplemente ligada
    public listaSimplementeLigada()
    {
        primero = null;
        ultimo = null;
    }
    
    //Retorna verdadero si la lista es vacia, de lo contrario retorna flaso
    public boolean esVacia()
    {
        return primero==null;
    }
    
    //Retorna el primer nodo de la lista ligada
    public nodoSimple primerNodo()
    {
        return primero;
    }
    
    //Retorna el ultimo nodo de la lista ligada
    public nodoSimple ultimoNodo()
    {
        return ultimo;
    }
    
    //Retorna el nodo anterior al nodo enviado como parametro
    public nodoSimple anterior(nodoSimple x)
    {
        nodoSimple anterior, p;
        p = primerNodo();
        anterior = null;
        while(p!=x)
        {
            anterior = p;
            p = p.retornaLiga();
        }
        return anterior;                
    }       
    
    //Retorna verdadero si el nodo x enviado como parametro es igual a nulo
    public boolean finDeRecorrido(nodoSimple x)
    {
        return x==null;
    }
    
    //Recorre y escribe los datos de la lista
    public void recorre()
    {
        System.out.println("Entra");
        nodoSimple p;
        p = primerNodo();
        while(!finDeRecorrido(p))
        {
            System.out.println(p.retornaDato());
            p = p.retornaLiga();
        }
        System.out.println("Sale");
    }
    
    //Retorna el nodo a continuacion del cual se de insertar un nuevo nodo
    //con dato d en una lista en que los datos esten ordenados ascendentemente
    public nodoSimple buscaDondeInsertar(int d)
    {
        nodoSimple ap, p;
        p = primerNodo();
        ap = anterior(p);
        while(!finDeRecorrido(p) && (int)p.retornaDato()<d)
        {
            ap = p;
            p = p.retornaLiga();         
        }
        return ap;
    }   
    
    //Consigue un nuevo nodo, lo carga con el dato d entrado como parametro e
    //invoca el nodo conectar a continuacion del nodo y entrado como parametro
    public void insertar(int d, int coef, nodoSimple y)
    {
        nodoSimple x;
        x = new nodoSimple(d, coef);
        conectar(x, y);
    }
    
    //Conecta el nodo x a continuacion del nodo y
    public void conectar(nodoSimple x, nodoSimple y)
    {
        if(y!=null)
        {
            x.asignaLiga(y.retornaLiga());
            y.asignaLiga(x);
            if(y==ultimo)
            {
                ultimo = x;
            }
        }
        else
        {
            x.asignaLiga(primero);
            if(primero == null)
            {
                primero = x;
                ultimo = x;
            }
            else
            {
                primero = x;
            }
        }        
    }
    
    //Busca el dato d entrado como parametro en la lista, retorna el nodo en el 
    //cual lo encontro, en el parametro y entrado como referencia se guarda el
    //nodo anterior al del nodo en el cual se encontro d
    public nodoSimple buscarDato(int d, nodoSimple y)
    {
        nodoSimple p = new nodoSimple(null, 0);
        return p;
    }
    
    //Controla que x sea diferende de null, si lo es invoca el metodo desconectar
    public void borrar(nodoSimple x, nodoSimple y)
    {
        if(x==null)
        {
            System.out.println("Dato no existe");
            return;
        }
        desconectar(x,y);
    }
    
    //Desconecta el nodo x de la lista
    public void desconectar(nodoSimple x, nodoSimple y)
    {
        if(x != primero)
        {
            y.asignaLiga(x.retornaLiga());
            if(x==ultimo)
            {
                ultimo = y;
            }
            else
            {
                primero = primero.retornaLiga();
                if(primero==null)
                {
                    ultimo = null;
                }
            }
        }
    }
    
    //
    public void ordenaAscendentemente()
    {
      nodoSimple p, ap, menor, amenor, q, aq;
      p = primerNodo();
      ap = anterior(p);
      while(p!=ultimoNodo())
      {
          menor = p;
          amenor = ap;
          q = p.retornaLiga();
          aq = p;
          while(!finDeRecorrido(p))
          {
              if((int)q.retornaDato()<(int)menor.retornaDato())
              {
                  menor = q;
                  amenor = aq;
              }
              aq = q;
              q = q.retornaLiga();
          }
          if(menor==p)
          {
              ap = p;
              p = p.retornaLiga();
          }
          else
          {
              desconectar(menor, amenor);
              conectar(menor, ap);
              ap = menor;
          }
      }
    }
}
