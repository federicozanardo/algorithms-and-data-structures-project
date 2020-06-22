/**
 * Questa classe implementa gli algoritmi necessari per effettuare le misurazioni dei tempi di esecuzione dell'algoritmo
 * risolutivo. Tali algortimi sono stati realizzati sulla base degli Appunti del docente.
 */
public class CostoComputazionale {

    private double seme = 5400;
    private double tempSeme;

    /**
     * Algoritmo 4 degli appunti.
     */
    private int granularita() {
        long tempoIniziale = System.currentTimeMillis();
        long tempoFinale = System.currentTimeMillis();

        while(tempoIniziale == tempoFinale) {
            tempoFinale = System.currentTimeMillis();
        }

        return (int)(tempoFinale - tempoIniziale);
    }

    /**
     * Errore massimo percentuale del 5% (come illustrato a pagina 8 degli appunti).
     */
    private int tempoMinimo() {
        return 20 * granularita();
    }

    /**
     * Algoritmo 5 degli appunti.
     */
    private int calcolaRipTara(int length) {
        long tempoIniziale = 0, tempoFinale = 0;
        int rip = 1;

        while ((tempoFinale - tempoIniziale) <= tempoMinimo()) {
            rip = rip * 2;                                      // stima di rip con crescita esponenziale
            tempoIniziale = System.currentTimeMillis();

            for(int i = 0; i < rip; i++) {
                generateRandomRationalValues(length);
            }
            tempoFinale = System.currentTimeMillis();
        }

        // Ricerca esatta del numero di ripetizioni per bisezione, approsimazione a 5 cicli

        int max = rip;
        int min = rip / 2;
        int cicliErrati = 5;

        while((max - min) >= cicliErrati) {
            rip = (max + min) / 2;                              // valore mediano
            tempoIniziale = System.currentTimeMillis();

            for(int i = 0; i < rip; i++) {
                generateRandomRationalValues(length);
            }
            tempoFinale = System.currentTimeMillis();

            if((tempoFinale - tempoIniziale) <= tempoMinimo()) {
                min = rip;
            }
            else {
                max = rip;
            }
        }

        return max;
    }

    private int calcolaRipLordo(int length) {
        long tempoIniziale = 0, tempoFinale = 0;
        int rip = 1;

        while ((tempoFinale - tempoIniziale) <= tempoMinimo()) {
            rip = rip * 2;                                      // stima di rip con crescita esponenziale
            tempoIniziale = System.currentTimeMillis();

            for(int i = 0; i < rip; i++) {
                Program.executeProgram(generateRandomRationalValues(length));
            }
            tempoFinale = System.currentTimeMillis();
        }

        // Ricerca esatta del numero di ripetizioni per bisezione, approsimazione a 5 cicli

        int max = rip;
        int min = rip / 2;
        int cicliErrati = 5;

        while((max - min) >= cicliErrati) {
            rip = (max + min) / 2;                              // valore mediano
            tempoIniziale = System.currentTimeMillis();

            for(int i = 0; i < rip; i++) {
                Program.executeProgram(generateRandomRationalValues(length));
            }
            tempoFinale = System.currentTimeMillis();

            if((tempoFinale - tempoIniziale) <= tempoMinimo()) {
                min = rip;
            }
            else {
                max = rip;
            }
        }

        return max;
    }

    /**
     * Corrisponde alla funzione Prepara. Si occupa di genereare e preparare dei dati randomici e di fornirli in input.
     * @param length    lunghezza dell'array di valori razionali positivi che deve essere generato
     * @return          array di valori razionali
     */
    private double[] generateRandomRationalValues(int length) {
        double tempSeme = seme;
        double[] data = new double[length];

        for(int i = 0; i < length; i++) {
            data[i] = randomGenerator();
            if(length == i + 1) {
                this.tempSeme = seme;
            }
        }

        seme = tempSeme;
        return data;
    }

    /**
     * Algoritmo 7 degli appunti.
     */
    private double getTempoMedioNetto(int length) {
        double tempoTara, tempoLordo;
        double[] datiInput;

        int ripTara = calcolaRipTara(length);
        int ripLordo = calcolaRipLordo(length);

        long tempoIniziale = System.currentTimeMillis();

        for(int i = 0; i < ripTara; i++) {
            datiInput = generateRandomRationalValues(length);
        }

        long tempoFinale = System.currentTimeMillis();

        // Tempo totale di esecuzione della tara
        tempoTara = tempoFinale - tempoIniziale;

        tempoIniziale = System.currentTimeMillis();

        for(int i = 0; i < ripLordo; i++) {
            datiInput = generateRandomRationalValues(length);
            Program.executeProgram(datiInput);
        }

        tempoFinale = System.currentTimeMillis();

        // Tempo totale di esecuzione del lordo
        tempoLordo = tempoFinale - tempoIniziale;

        // Aggiorno il nuovo seme per poter generare nuovi numeri randomici
        seme = tempSeme;

        return tempoLordo / ripLordo - tempoTara / ripTara; // tempo medio di esecuzione
    }

    /**
     * Algoritmo 8 degli appunti.
     */
    private double randomGenerator() {
        double a = 16807, m = 2147483647, q = 127773, r = 2836;

        double hi = (int)(seme / q);
        double lo = seme - q * hi;
        double test = a * lo - r * hi;

        if(test < 0) {
            seme = test + m;
        }
        else {
            seme = test;
        }

        return seme / m;
    }

    /**
     * Algoritmo 9 degli appunti.
     */
    public double[] misurazione(int length, int c, double za, double deltaMin) {
        double t = 0, sum2 = 0, cn = 0, delta, m, e, s;
        double[] results = new double[2];

        do {
            for(int i = 1; i < c; i++) {
                m = getTempoMedioNetto(length);
                t = t + m;
                sum2 = sum2 + (m * m);
            }

            cn = cn + c;
            e = t / cn;
            s = Math.sqrt((sum2 / cn) - (e * e));
            delta = (1 / Math.sqrt(cn)) * za * s;
        } while (delta > deltaMin);

        results[0] = e;
        results[1] = delta;

        return results;
    }
}
