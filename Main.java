import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        String standardInput;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        /*
         * Viene gestito il caso in cui non vi sia alcun valore come input.
         * Se non si presenta questo caso, viene avviato il programma per calcolare la mediana inferiore pesata.
         */
        while((standardInput = bufferedReader.readLine()) != null) {
            if(validateInput(standardInput)) {
                calculateLowerWeightedMedian(standardInput);
            }
            else {
                nothing();
            }
        }
    }

    private static boolean validateInput(String standardInput) {
        return !standardInput.trim().equals("") && !standardInput.trim().equals(".");
    }

    private static void calculateLowerWeightedMedian(String standardInput) {
        System.out.println(Program.execute(standardInput));
    }

    private static void nothing() {
        System.out.println();
    }
}
