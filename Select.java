public class Select {

    /**
     * Algoritmo Select.
     * @param rationalValues    array di valori razionali positivi
     * @param begin             indice di inizio della porzione di array
     * @param end               indice di fine della porzione di array
     * @param k                 posizione dell'elemento che si vuole trovare, se l'array fosse ordinato
     * @return                  ritorna l'elemento che si troverebbe in posizione k se l'array fosse ordinato
     */
    public static double select(double[] rationalValues, int begin, int end, int k) {

        /*
         * Se la dimensione dell'array è minore o uguale a 5, allora viene chiamato InsertionSort. L'utilizzo di questo
         * ultimo algoritmo non aumenta la complessità globale dell'algoritmo, in quanto esso viene chiamato su un array
         * che ha sempre una dimensione compresa tra 0 e 5. Quindi la complessità computazionale sarà sempre O(1).
         */
        if(end - begin <= 5) {
            InsertionSort.sort(rationalValues, begin, end);
            return rationalValues[k];
        }

        // Ottengo la dimensione dell'array che conterrà le mediande di ogni gruppo
        int lengthOfMedians = getLengthOfMediansArray(begin, end);

        double[] medians = new double[lengthOfMedians];
        int counter = 0;

        /*
         * In questo ciclo, viene effettuato un incremento di 5 perchè si va a calcolare la mediana di ogni singolo
         * gruppo, essendo ogni gruppo costituito da 5 elementi.
         */
        for(int j = begin; j <= end; j += 5) {
            if(end - j >= 5) {
                medians[counter] = getMedian(rationalValues, j, j + 4);
            }
            else {
                medians[counter] = getMedian(rationalValues, j, end);
            }
            counter++;
        }

        // Ottengo la mediana delle mediane
        double valueOfMedian = getMedianValueFromMedians(medians);

        // A partire dal valore della mediana, ottengo la sua posizione
        int indexOfMedian = getMedianValueIndexFromMedians(rationalValues, begin, end, valueOfMedian);

        /*
         * Chiamando la procedura Partition sull'indice della mediana, riuscirò ad ottenere la posizione esatta che ha
         * nell'array se quest'ultimo fosse ordinato.
         */
        int index = partition(rationalValues, begin, end, indexOfMedian);

        /*
         * Ritorno l'elemento che si stava cercando nella posizione k se il risultato di partition è uguale a k,
         * altrimenti determino su quale porzione di array mi devo spostare, facendo una chimata ricorsiva con Select.
         */
        if(index == k) {
            return rationalValues[k];
        }
        else {
            if(k < index) {
                return select(rationalValues, begin, index - 1, k);
            }
            else {
                return select(rationalValues, index + 1, end, k);
            }
        }
    }

    /**
     * Questa procedura permette di ottenere la dimensione che dovrà avere l'array delle mediane.
     * @param begin indice di inizio della porzione di array
     * @param end   indice di fine della porzione di array
     * @return      lunghezza dell'array delle mediane
     */
    private static int getLengthOfMediansArray(int begin, int end) {
        int integerPortion = (end - begin + 1) / 5, fractionalPortion = 0, val = (end - begin + 1);

        if((val % 5 != 0)) {
            fractionalPortion = 1;
        }

        return integerPortion + fractionalPortion;
    }

    /**
     * Viene chiamato InsertionSort per ordinare la porzione di array indicata dai due indici passati in input.
     * Viene infine ritornata la mediana della porzione di array in questione.
     * @param rationalValues    dati che devono essere ordinati dall'algoritmo
     * @param begin             indice di inizio della porzione da ordinare
     * @param end               indice di fine della porzione da ordinare
     * @return                  mediana della porzione delimitata
     */
    private static double getMedian(double[] rationalValues, int begin, int end) {
        InsertionSort.sort(rationalValues, begin, end);
        return rationalValues[begin + (end - begin) / 2];
    }

    /**
     * Questa procedura permette di ottenere la mediana delle mediane, facendo una chiamata ricorsiva di select
     * sull'array delle mediane.
     * @param medians   array delle mediane
     * @return          mediana delle mediane
     */
    private static double getMedianValueFromMedians(double[] medians) {
        return select(medians, 0, medians.length - 1, (medians.length - 1) / 2);
    }

    /**
     * A partire dal valore della mediana, ottengo la sua posizione nell'array.
     * @param rationalValues    array dei valori
     * @param begin             inizio della porzione dell'array
     * @param end               fine della porzione dell'array
     * @param medianValue       valore della mediana
     * @return                  posizione della mediana nell'array
     */
    private static int getMedianValueIndexFromMedians(double[] rationalValues, int begin, int end, double medianValue) {
        int counter = begin, indexOfMedian = 0;
        boolean isIndexOfMedian = false;

        while(counter <= end && !isIndexOfMedian) {
            if(rationalValues[counter] == medianValue) {
                indexOfMedian = counter;
                isIndexOfMedian = true;
            }
            counter++;
        }
        return indexOfMedian;
    }

    /**
     * Algoritmo Partition.
     * @param rationalValues    array dei valori razionali
     * @param begin             indice di inizio della porzione di array
     * @param end               indice di fine della porzione di array
     * @param pivot             pivot
     * @return                  posizione
     */
    public static int partition(double[] rationalValues, int begin, int end, int pivot) {
        swap(rationalValues, pivot, end);
        double currentPivot = rationalValues[end];
        int i = begin - 1;

        for(int j = begin; j <= end; j++) {
            if(rationalValues[j] <= currentPivot) {
                i++;
                swap(rationalValues, i, j);
            }
        }
        return i;
    }

    /**
     * Esegue lo scambio di due valori di un array, forniti i rispettivi indici.
     * @param rationalValues        array contenente i valori che devono essere scambiati
     * @param firstIndex            indice del primo valore
     * @param secondIndex           indice del secondo valore
     */
    private static void swap(double[] rationalValues, int firstIndex, int secondIndex) {
        double temp = rationalValues[firstIndex];
        rationalValues[firstIndex] = rationalValues[secondIndex];
        rationalValues[secondIndex] = temp;
    }
}
