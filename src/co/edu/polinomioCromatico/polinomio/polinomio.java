package co.edu.polinomioCromatico.polinomio;

import java.math.BigInteger;


public class polinomio extends listaSimplementeLigada
{
    private int coef;
    private int pot;
    
    //Crea el polinomio constante p(x) = 1
    public polinomio()
    {
        nodoSimple y= new nodoSimple(0,0);
        //y = buscaDondeInsertar(0);
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
    
    public polinomio(String polinomio)
    {
        String[] letras;
        double d;
        int p;
        letras = separarPolinomio(polinomio);
        if(letras[0].equals(""))
        {
            d = 1;
        }
        else
        {
            d = Double.parseDouble(letras[0]);
        }
        p = Integer.parseInt(letras[1]);
        insertar(d, p, null);
        nodoSimple nodo = primerNodo();
        for(int i=2;i<letras.length;i++)
        {
            if(letras[i]!=null)
            {
            if(letras[i].equals("+"))
            {
                d = 1;
            }
            else if(letras[i].equals("-"))
            {
                d = -1;
            }
            else
            {
                d = Double.parseDouble(letras[i]);               
            }
            i = i+1;
            if(i==letras.length)
            {
                break;
            }                
            else
            {
                p = Integer.parseInt(letras[i]);
            }
            insertar(d, p, nodo);
            nodo = nodo.retornaLiga();
            }
        }
    }
    
    
     public String[] separarPolinomio(String polinomio)
    {
        String[] letras;
        int c;
        int j;
        polinomio = polinomio.toLowerCase();
        polinomio = polinomio.replace("  ", "");
        polinomio = polinomio.replace(" ", "");
        polinomio = polinomio.replace("+", " +");
        polinomio = polinomio.replace("-", " -");
        String[] fracciones = polinomio.split(" ");
        c=(fracciones.length)*2;
        letras= new String[c];
        j =0;
        if (fracciones[0].equals(""))
        {
            for (int i=1;i<fracciones.length;i++) 
            {
               String[] digito = fracciones[i].split("x");
               if (digito.length==2) 
               {
                    letras[j]=digito[0];
                    j++;
                    letras[j]=digito[1];
                    j++;
               } 
               else if(digito.length==1) 
               {
                   letras[j]=digito[0];
                   j++;
                   if (fracciones[i].indexOf("x")==(-1))
                   {
                       letras[j]="0";
                       j++;
                   } 
                   else
                   {
                       letras[j]="1";
                       j++;
                   }
               }
               else
               {
                   letras[j]="1";
                   j++;
                   letras[j]="1";
                   j++;
               }  
            }
        } 
        else
        {
            for (int i=0;i<fracciones.length;i++) 
            {
               String[] digito = fracciones[i].split("x");
               if (digito.length==2) 
               {
                    letras[j]=digito[0];
                    j++;
                    letras[j]=digito[1];
                    j++;
               } 
               else if(digito.length==1) 
               {
                   letras[j]=digito[0];
                   j++;
                   if (fracciones[i].indexOf("x")==(-1)) 
                   {
                       letras[j]="0";
                       j++;
                   } 
                   else
                   {
                       letras[j]="1";
                       j++;
                   }
               }
               else
               {
                   letras[j]="1";
                   j++;
                   letras[j]="1";
                   j++;
               }
            }
        }
         return letras;
    }
    
  
    
    //Retorna el grado del polinomio
    public int grado()
    {
        return primerNodo().retornaPotencia();
    }
    
    
///////////multiplicar/////////////////////////////////////////////////    
   public polinomio multiplicar(polinomio polinomio)
    {
       polinomio resultado;
       nodoSimple p,q, o;
       Double d;
       int pot;
       resultado = new  polinomio();
       o = resultado.primerNodo();
       p = primerNodo();
       q = polinomio.primerNodo();
       while (p!=null)
       {
            d = (Double)p.retornaDato();
            while (q!=null)
            {
                d = d*(Double)q.retornaDato();
                pot = (Integer)p.retornaPotencia()+(Integer)q.retornaPotencia();
                o = resultado.buscaDondeInsertar(pot);
                
                resultado.insertar(d, pot, o);
                q = q.retornaLiga();
            }
            p = p.retornaLiga();   
            q = polinomio.primerNodo();
        }
        return resultado;        
    }
    
    
//////////////////simplificar el polinomio/////////////////////////    
     public void simplificar(listaSimplementeLigada list){
        nodoSimple uno = list.primerNodo();
        nodoSimple dos = uno.retornaLiga();
        
        while(!list.finDeRecorrido(dos)){
            
            if(igualExp(uno,dos)){
                sumarNodo(uno,dos,list);
            }else{
                uno=uno.retornaLiga();
                dos=dos.retornaLiga();
            }
        }
        
    }
    
////////////////////igual exponente//////////////////////////////////////
      public boolean igualExp(nodoSimple x, nodoSimple y){
    
        int exp1,exp2;
        
        exp1=(int)x.retornaPotencia();
        exp2=(int)y.retornaPotencia();
        
        if(exp1==exp2){
            return true;
        }else{
            return false;
        }
    }
      
/////////////////////sumar nodos////////////////////////////////////////
        public void sumarNodo(nodoSimple uno, nodoSimple dos, listaSimplementeLigada result){
        
        float a,b,r;
        String x,y;
       nodoSimple nuevo;

            a=(float) uno.retornaDato();
            b=(float) dos.retornaDato();

        r=a+b;
        nuevo =new nodoSimple(r,uno.retornaPotencia());
 
        result.borrar(uno);
        result.borrar(dos);
        result.insertarordenado(nuevo);
        uno=nuevo;
        dos=nuevo.retornaLiga();
        
    }
   
///////////////////////evaluar polinomio/////////////////////////////    
    public double evaluar(polinomio list, int x){
        
       nodoSimple nod;
       double result=0;
       nod= list.primerNodo();
       
       while(!list.finDeRecorrido(nod)){
           
               result= result+(double)nod.retornaDato()* Math.pow(x, nod.retornaPotencia());
           
          
            nod=nod.retornaLiga();
       }
               
       
       return result;
    }
  
/////////////////////////es factor/////////////////////////////    
    public String esFactor(polinomio list, int c){
       
        double x = this.evaluar(list, c);
        if (x==0) {
            return "Es factor";
        } else {
            return "No es factor";
        }
    }
    
//////////////////////longitud///////////////////////////////    
     public int longitud() //Retorna la longitud de la lista
    {
        nodoSimple p;
        p = primerNodo();
        int i = 0;
        while(!finDeRecorrido(p))
        {
           i=i+1;
           p = p.retornaLiga();
        }
        return i;
    }
     
///////////////////////////sumar//////////////////////////////    
     public polinomio suma(polinomio polinomio)
    {
       polinomio resultado;
       nodoSimple p,q, o, nodo;
       Double d;
       int longitudP,longitudQ;
       resultado = new  polinomio();
       longitudP = longitud();
       longitudQ = polinomio.longitud();
       o = resultado.primerNodo();
       
       
       if(longitudP>longitudQ)
       {
           p = primerNodo();
           q = polinomio.primerNodo();
       }
       else
       {
           p = polinomio.primerNodo();
           q = primerNodo();
       }
       
       
       
        while (p!=null)
        {
            d = (Double)p.retornaDato(); 
            o = resultado.buscaDondeInsertar(p.retornaPotencia());                      
//            
            resultado.insertar(d, p.retornaPotencia(), o);
            p = p.retornaLiga();           
        }
        
        while (q!=null)
        {
            d = (Double)q.retornaDato();
            o = resultado.buscaDondeInsertar(q.retornaPotencia());                      
            resultado.insertar(d, q.retornaPotencia(), o);
            q = q.retornaLiga();           
        }
       
        nodo= resultado.primerNodo();
        for (int i = 0; i < resultado.longitud()-1; i++) {
            
            if (nodo.retornaPotencia()==nodo.retornaLiga().retornaPotencia()) {
                
                nodo.asignaDato((double)nodo.retornaDato()+ (double)nodo.retornaLiga().retornaDato());
                resultado.borrar(nodo.retornaLiga());
                i--;
            }else {
                nodo=nodo.retornaLiga();
            }
        }
        
        return resultado;        
    }
    
    
     
////////////////imprimir polinomio/////////////////////
      public String imprimePolinomio()
    {
        
        String sPolinomio = "";
        String d = "";
        String pot = "";
       
        nodoSimple p = this.primerNodo();
        double m=(double) p.retornaDato();
        while(!this.finDeRecorrido(p))
        {
            if(m>0)
            {
                if(p.retornaPotencia()==this.primerNodo().retornaPotencia())
                {
                    if((double)p.retornaDato()==1)
                    {
                        d="";
                    }
                    else
                    {
                        d=""+p.retornaDato();
                    }
                }
                else
                {
                    d ="+"+p.retornaDato();
                }
            }
            else if((Double)p.retornaDato()==1 && (Integer)p.retornaPotencia()>0)
            {
                d="";
            }
            else if((Double)p.retornaDato()==1 && (Integer)p.retornaPotencia()<0)
            {
                d="-";
            }
            else if((Double)p.retornaDato()==0)
            {
                d="";
            }
            else
            {
                d =""+p.retornaDato();
            }
            if ((Integer)p.retornaPotencia()>0)
            {
                pot = "x^"+p.retornaPotencia();
            }
            else
            {
                pot = "";
            }
            sPolinomio = sPolinomio+""+d+""+pot;
            p = p.retornaLiga();
        }
        return sPolinomio;
    }
    
}
