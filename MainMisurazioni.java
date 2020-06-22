/**
 * Questa classe permette per ottenere i tempi di esecuzione dell'algoritmo. I vari risultati vengono memorizzati in un
 * file di testo (risultati.txt).
 */

public class MainMisurazioni {

    public static void main(String[] args) {

        int legnthOfArray = 0;
        CostoComputazionale costoComputazionale = new CostoComputazionale();
        RegistraMisurazioni registraMisurazioni = new RegistraMisurazioni("risultati.txt");

        while(true) {
            legnthOfArray = legnthOfArray + 200;
            double[] results = costoComputazionale.misurazione(legnthOfArray, 50, 1.96d, 0.02);
            registraMisurazioni.saveResultsOnFile(results, legnthOfArray);
        }
    }
}
