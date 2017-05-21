package co.edu.polinomioCromatico.modelo.grafo;

public class matrizForma2
{
    private nodoDoble mat;
    
    //Constructor
    public matrizForma2(int m, int n)
    {
        tripleta t = new tripleta(m, n, null);
        mat = new nodoDoble(t);
        tripleta tx = new tripleta(0, 0, null);
        nodoDoble x = new nodoDoble(tx);
        x.asignaLi(x);
        x.asignaLd(x);
        mat.asignaLd(x);
    }
    
    public nodoDoble primerNodo()
    {
        return mat;
    }
    
    public nodoDoble nodoCabeza()
    {
        return mat.retornaLd();
    }
    
    public boolean esVacia()
    {
        nodoDoble p = mat.retornaLd();
        return (p.retornaLi()==p && p.retornaLd()==p);
    }
    
    public boolean finDeRecorrido(nodoDoble p)
    {
        return p == nodoCabeza();
    }
    
    public void muestraMatriz()
    {
        int qf, qc, qv;
        nodoDoble q;
        tripleta tq;
        q = nodoCabeza().retornaLd();
        while(!finDeRecorrido(q))
        {
            tq = (tripleta)q.retornaDato();
            qf = tq.retornaFila();
            qc = tq.retornaColumna();
            qv = (int)tq.retornaValor();
            System.out.println(qf+""+qc+""+qv);
            q = q.retornaLd();
        }
    }
    
    public nodoDoble retonarNodoEn(int f, int c)
    {
        nodoDoble p;
        tripleta t;
        p = primerNodo();
        while(p!=nodoCabeza())
        {
            t = (tripleta)p.retornaDato();
            if(t.retornaFila()==f && t.retornaColumna()==c)
            {
                return p;
            }
            else
            {
                p = p.retornaLd();
            }
        }
        return p;
    }
    
    public void conectaPorColumnasForma2(nodoDoble x)
    {
        tripleta tx = (tripleta)x.retornaDato();
        nodoDoble p = nodoCabeza();
        nodoDoble anterior = p;
        nodoDoble q = p.retornaLi();
        tripleta tq = (tripleta)q.retornaDato();
        while(q!=p && tq.retornaColumna()<tx.retornaColumna())
        {
            anterior = q;
            q = q.retornaLi();
            tq = (tripleta)q.retornaDato();
        }
        while(q!=p && tq.retornaColumna()==tx.retornaColumna() && tq.retornaFila()<tx.retornaFila())
        {
            anterior = q;
            q = q.retornaLi();
            tq = (tripleta)q.retornaDato();
        }
        anterior.asignaLi(x);
        x.asignaLi(q);
//        tripleta tmat = (tripleta)mat.retornaDato();
//        tmat.asignaValor((int)tmat.retornaValor()+1);
    }
    
    public void conectaPorFilasForma2(nodoDoble x)
    {
        tripleta tx = (tripleta)x.retornaDato();
        nodoDoble p = nodoCabeza();
        nodoDoble anterior = p;
        nodoDoble q = p.retornaLd();
        tripleta tq = (tripleta)q.retornaDato();
        while(q!=p && tq.retornaFila()<tx.retornaFila())
        {
            anterior = q;
            q = q.retornaLd();
            tq = (tripleta)q.retornaDato();
        }
        while(q!=p && tq.retornaFila()==tx.retornaFila() && tq.retornaColumna()<tx.retornaColumna())
        {
            anterior = q;
            q = q.retornaLd();
            tq = (tripleta)q.retornaDato();
        }
        anterior.asignaLd(x);
        x.asignaLd(q);
    }
    
}
