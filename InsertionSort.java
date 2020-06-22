public class InsertionSort {

    /**
     * Algoritmo InsertionSort
     * La versione di questo algoritmo, a differenza da quella tradizionale, ha due parametri aggiuntivi che indicano
     * l'inizio e la fine della porzione di array che deve essere ordinata
     * @param rationalValues    dati che devono essere ordinati dall'algoritmo
     * @param start             indice di inizio della porzione da ordinare
     * @param end               indice di fine della porzione da ordinare
     */
    public static void sort(double[] rationalValues, int start, int end) {
        int j;
        double rationalValue;

        for(int i = start; i <= end; i++) {

            rationalValue = rationalValues[i];
            j = i - 1;

            while(j >= start && rationalValues[j] > rationalValue) {
                rationalValues[j + 1] = rationalValues[j];
                j = j - 1;
            }
            rationalValues[j + 1] = rationalValue;
        }
    }
}
