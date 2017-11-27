package lab;

public class Punto6 {

    private final static int CANTIDAD_BLANCAS = 6;
    private final static int CANTIDAD_ROJAS = 4;
    private final static int CANTIDAD_AZULES = 4;

    private static int casosTotales = 0, ganar10 = 0, ganar15 = 0, ganar20 = 0;

    public static void main(String[] args) {
        recursivoEvaluarYSacarBola(0, 0, 0);
        System.out.println("GANAR 10: " + ganar10 + "/" + casosTotales + "      (" + (double) ganar10 / (double) casosTotales + ")");
        System.out.println("GANAR 15: " + ganar15 + "/" + casosTotales + "      (" + (double) ganar15 / (double) casosTotales + ")");
        System.out.println("GANAR 20: " + ganar20 + "/" + casosTotales + "      (" + (double) ganar20 / (double) casosTotales + ")");
    }

    public static void recursivoEvaluarYSacarBola(int blancas, int rojas, int azulez) {
        if (blancas > CANTIDAD_BLANCAS || rojas > CANTIDAD_ROJAS || azulez > CANTIDAD_AZULES) {
            return;
        }

        if (blancas + rojas + azulez == 10) {
            casosTotales++;
            if (blancas == 6 && rojas + azulez == 4) {
                ganar10++;
            }
            if (blancas == 2 && rojas == 4 && azulez == 4) {
                ganar15++;
            }
            if (blancas == 6 && rojas == 4 || blancas == 6 && azulez == 4) {
                ganar20++;
            }
            return;
        }

        recursivoEvaluarYSacarBola(blancas + 1, rojas, azulez); 
        recursivoEvaluarYSacarBola(blancas, rojas + 1, azulez);
        recursivoEvaluarYSacarBola(blancas, rojas, azulez + 1);
    }
}