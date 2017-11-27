package lab;

import java.util.Arrays;

/**
 * Si Tengo que sacar 3 bolas de una bolsa y en la bolsa tengo 2 bolas de cada
 * color (blanco, rojo, azul) Cual es la probabilidad de sacar 2 blancas y una
 * cualquiera?
 */
public class SmallTest2 {

    public static void main(String[] args) {
        int casosTotales = 0, casoDeseado = 0;

        char[] arrayBolas = "bbrraa".toCharArray();
        Arrays.sort(arrayBolas);
        do {
            casosTotales++;
            int cantidadBlancas = 0, cantidadRojas = 0, cantidadAzules = 0;
            for (int i = 0; i < 3; i++) {
                if (arrayBolas[i] == 'b') cantidadBlancas++;
                else if (arrayBolas[i] == 'r') cantidadRojas++;
                else if (arrayBolas[i] == 'a') cantidadAzules++;
            }
            
            String sirve = "";
            if (cantidadBlancas == 2 && cantidadRojas + cantidadAzules == 1) {
                casoDeseado++;
                sirve = "  SIRVE";
            }

            System.out.printf("Permutacion #%02d: %s %s\n", casosTotales, Arrays.toString(arrayBolas), sirve);
        } while (nextPermutation(arrayBolas));

        System.out.println("CasosDeseados/CasosTotales: " + casoDeseado + "/" + casosTotales);
        System.out.println("Probabilidad: " + (double) casoDeseado / (double) casosTotales);
    }

    public static boolean nextPermutation(char[] array)
    {
        int i=array.length-1;
        while(i>0 && array[i-1]>=array[i]) i--;
        
        if(i==0) return false;
        
        int j=array.length-1;
        while(array[j]<=array[i-1]) j--;
        
        char temp=array[i-1];
        array[i-1]=array[j];
        array[j]=temp;
        
        j=array.length-1;
        while(i<j)
        {
            temp=array[i];
            array[i]=array[j];
            array[j]=temp;
            i++; j--;
        }
        return true;
    }
}
