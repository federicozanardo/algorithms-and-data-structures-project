/**
 * Questa classe si occupa di convertire i dati forniti in input, che sono sottoforma di stringa, in un array di valori
 * razionali e di chiamare il metodo che permetterà di calcolare la mediana inferiore pesata.
 */

public class Program {

    /**
     * Questo metodo si occupa di convertire i dati (in formato stringa) in un array di valori razionali.
     * Successivamente chiama il metodo che calcolerà la mediana inferiore pesata, fornendo in input l'array di valori
     * razionali.
     * @param valuesToString    dati che devono essere convertiti in valori razioali
     * @return                  mediana inferiore pesata
     */
    public static double execute(String valuesToString) {
        double[] rationalValues = prepareInputData(valuesToString);
        return executeProgram(rationalValues);
    }

    /**
     * Questo metodo si occupa di convertire i valori, che sono salvati in una stringa, in un array di valori razionali.
     * @param stringToParse     dati che devono essere convertiti in valori razioali
     * @return                  array di valori razionali
     */
    private static double[] prepareInputData(String stringToParse) {
        int arrayIndex = 0, stringIndex = 0, lengthOfArray;
        boolean canContinue = true;
        String numberToConvert = "";

        // Pulisco la stringa
        stringToParse = stringToParse.trim();

        // Ottengo la dimensione dell'array che dovrà contentere i valori razionali
        lengthOfArray = countDigitsFromString(stringToParse);
        double[] rationalValues = new double[lengthOfArray];

        // Conversione dei dati dal formato stringa in valori razionali
        while(canContinue) {

            // Significa che si è raggiunta la fine della stringa di input
            if(stringToParse.charAt(stringIndex) == '.' && stringToParse.length() == stringIndex + 1) {
                rationalValues[arrayIndex] = Double.parseDouble(numberToConvert);
                canContinue = false;
            }

            // Significa che ho terminato la lettura di un numero dalla stringa
            if(stringToParse.charAt(stringIndex) == ',') {
                rationalValues[arrayIndex] = Double.parseDouble(numberToConvert);
                arrayIndex++;
                numberToConvert = "";
            }
            else {
                numberToConvert = numberToConvert + stringToParse.charAt(stringIndex);
            }
            stringIndex++;
        }

        return rationalValues;
    }

    /**
     * Questo metodo chiama la rispettiva funzione che permetterà di ottenere la mediana inferiore pesata.
     * @param rationalValues    array di valori razionali
     * @return                  mediana inferiore pesata
     */
    public static double executeProgram(double[] rationalValues) {
        LowerWeightedMedian lowerWeightedMedian = new LowerWeightedMedian();
        return lowerWeightedMedian.getLowerWeightedMedian(rationalValues);
    }

    /**
     * Questo metodo si occupa di contare, a partire dalla stringa di input, quanti valori sono contenuti in tale
     * stringa.
     * @param stringToParse     dati che devono essere convertiti in valori razioali
     * @return                  numero di valori razionali presenti nella stringa
     */
    private static int countDigitsFromString(String stringToParse) {
        int k = 1;

        for(int i = 0; i < stringToParse.length(); i++) {
            if(stringToParse.charAt(i) == ',') {
                k++;
            }
        }

        return k;
    }
}
