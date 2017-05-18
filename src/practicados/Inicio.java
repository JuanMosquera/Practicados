package practicados;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Inicio
{

    public static void main(String[] args) 
    {
        int m = 0, n = 0, f = 0, c = 0, v = 0;
        tripleta t;
        nodoDoble x;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try
        {
            m = Integer.parseInt(br.readLine());
            n = Integer.parseInt(br.readLine());
        }catch(IOException ex){}
        matrizForma2 a = new matrizForma2(m, n);
        for(int i=1; i<3;i++)
        {
            try
            {
                System.out.println("Ingrese la fila: ");
                f = Integer.parseInt(br.readLine());
                System.out.println("Ingrese la columna: ");
                c = Integer.parseInt(br.readLine());
                System.out.println("Ingrese el valor: ");
                v = Integer.parseInt(br.readLine());
            }catch(IOException ex){}
            t = new tripleta(f,c,v);
            x = new nodoDoble(t);
            a.conectaPorFilasForma2(x);
            a.conectaPorColumnasForma2(x);
        }
    }
    
}
