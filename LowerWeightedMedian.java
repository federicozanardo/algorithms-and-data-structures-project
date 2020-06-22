/**
 * Questa classe implementa i metodi che permettono di calcolare la mediana inferiore pesata.
 */

public class LowerWeightedMedian {

    private double halfSum = 0;
    private double leftSum = 0;

    /**
     * Calcola la metà della somma e prepara i dati per il metodo che verrà chiamato successivamente, il quale
     * calcolerà effettivamente la mediana inferiore pesata.
     * @param rationalValues    array di valori razionali positivi
     * @return                  mediana inferiore pesata
     */
    public double getLowerWeightedMedian(double[] rationalValues) {
        double sum = 0;

        for(int i = 0; i < rationalValues.length; i++) {
            sum += rationalValues[i];
        }
        halfSum = sum / 2;

        return getLowerWeightedMedian(rationalValues, 0, rationalValues.length - 1, (rationalValues.length - 1) / 2, false);
    }

    /**
     * A partire dall'array fornito in input, permette di ottenere la mediana inferiore pesata.
     * @param rationalValues    array di valori razionali positivi
     * @param begin
     * @param end
     * @param k
     * @param goLeft            serve per ricordarsi in quale porzione dell'array ci si trovava nella precedente computazione
     * @return
     */
    private double getLowerWeightedMedian(double[] rationalValues, int begin, int end, int k, boolean goLeft) {

        int j = 0, valueIndex = 0;
        double rightSum = 0, value = 0;
        boolean found = false;

        // Ottengo il valore che si dovrebbe trovare nella posizione fornita in input, se l'array fosse ordinato
        value = Select.select(rationalValues, begin, end, begin + (end - begin) / 2);

        // Determino la posizione del valore restituito da Select nell'array
        while(j <= end && !found) {
            if(value == rationalValues[j]) {
                valueIndex = j;
                found = true;
            }
            j++;
        }

        int index = Select.partition(rationalValues, begin, end, valueIndex);

        /*
         * Nel primo ramo della condizione, la chiamata ricorsiva precedente ha effettuato la chiamata ricorsiva
         * corrente sulla porzione sinistra dell'array, rispetto alla chiamata ricorsiva precedente. Nell'altro
         * ramo della condizione, è stata effettuata una chiamata ricorsiva sulla porzione di destra dell'array.
         */
        if(goLeft) {
            rightSum = 0;

            for(int i = end; i >= index; i--) {
                rightSum = rightSum + rationalValues[i];
            }

            leftSum = leftSum - rightSum;
        }
        else {
            for(int i = begin; i < index; i++) {
                leftSum = leftSum + rationalValues[i];
            }
        }

        // Se mi trovo in questa casistica, significa che ho trovato la mediana inferiore pesata
        if(leftSum < halfSum && (leftSum + rationalValues[index]) >= halfSum) {
            return rationalValues[index];
        }

        // Significa che mi devo spostare nella porzione di destra per trovare il valore
        if(leftSum < halfSum) {
            leftSum = leftSum + rationalValues[index];
            return getLowerWeightedMedian(rationalValues, index + 1, end, k, false);
        }

        // La somma di sinistra ha sforato la metà della somma totale, quindi devo spostarmi nella porzione di sinistra
        else {
            return getLowerWeightedMedian(rationalValues, begin, index - 1, k, true);
        }
    }
}
