import java.io.*;

/**
 * Questa classe si occupa di ricevere i dati di input e di memorizzarli in un file di testo. Implementa le funzionalità
 * di base per la gestione del file.
 */

public class RegistraMisurazioni {

    private File resultsFile;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;

    public RegistraMisurazioni(String fileName) {
        this.resultsFile = new File(fileName);
    }

    /**
     * Questo metodo permette di gestire correttamente l'apertura e la chiusura del file e permette di registrare i
     * tempi di esecuzione dell'algortimo.
     * @param computationResults    risultati dei dati elaborati (tempo di esecuzione ed errore)
     * @param lengthOfArray         lunghezza dell'array che è stato elaborato
     */
    public void saveResultsOnFile(double[] computationResults, int lengthOfArray) {
        openFile();
        writeResultsOnFile(computationResults, lengthOfArray);
        closeFile();
    }

    /**
     * Questo metodo permette di aprire correttamente il file di testo (in append).
     */
    private void openFile() {
        try {
            this.fileWriter = new FileWriter(resultsFile, true);
            this.bufferedWriter = new BufferedWriter(fileWriter);
            this.printWriter = new PrintWriter(bufferedWriter);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Questo metodo permette di chiudere correttamente il file di testo.
     */
    private void closeFile() {
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Questo metodo scrive i tempi di esecuzione dell'algoritmo nel file di testo.
     * @param computationResults     contiene il tempo di esecuzione dell'algoritmo e il relativo errore
     * @param sizeOfData             dimensione dell'array su cui è stata fatta la misurazione
     */
    private void writeResultsOnFile(double[] computationResults, int sizeOfData) {
        printWriter.print("[" + sizeOfData + "] ");
        printWriter.printf("%.9f", computationResults[0]);
        printWriter.print(" - ");
        printWriter.printf("%.9f", computationResults[1]);
        printWriter.println();
    }
}
