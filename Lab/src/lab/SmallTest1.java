package lab;
/**
 * Si Tengo que sacar 3 bolas de una bolsa y en la bolsa tengo 2 bolas de cada color (blanco, rojo, azul)
 * Cual es la probabilidad de sacar 2 blancas y una cualquiera?
 */
public class SmallTest1 {

    private final static int CANTIDAD_BLANCAS = 2;
    private final static int CANTIDAD_ROJAS = 2;
    private final static int CANTIDAD_AZULES = 2;

    private static int casosTotales = 0, casoDeseado = 0;

    public static void main(String[] args) {
        recursivo(0, 0, 0, "");
        System.out.println("CasosDeseados/CasosTotales: " + casoDeseado + "/" + casosTotales);
        System.out.println("Probabilidad: " + (double) casoDeseado / (double) casosTotales);
    }

    public static void recursivo(int blancas, int rojas, int azulez, String pasos) {
        if (blancas > CANTIDAD_BLANCAS || rojas > CANTIDAD_ROJAS || azulez > CANTIDAD_AZULES) {
            return;
        }

        if (blancas + rojas + azulez == 3) {
            casosTotales++;
            System.out.print(pasos);
            if (blancas == 2 && rojas + azulez == 1) {
                casoDeseado++;
                System.out.print(" SIRVE");
            }
            System.out.println("");
            return;
        }

        recursivo(blancas + 1, rojas, azulez, pasos + (blancas + 1) + "   " + rojas + "   " + azulez + "    =>   ");
        recursivo(blancas, rojas + 1, azulez, pasos + blancas + "   " + (rojas + 1) + "   " + azulez + "    =>   ");
        recursivo(blancas, rojas, azulez + 1, pasos + blancas + "   " + rojas + "   " + (azulez + 1) + "    =>   ");

    }
}
