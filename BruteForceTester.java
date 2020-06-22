import java.util.Random;

public class BruteForceTester {
    public static void main(String[] args) {

        Random random = new Random();
        QuickSort quickSort = new QuickSort();
        int lengthArray;
        int counter = 1;
        boolean canContinue = true;
        double[] rationalValues = {0.1, 0.35, 0.05, 0.1, 0.15, 0.05, 0.2};

        while(canContinue) {

            lengthArray = random.nextInt(50000) + 1;

            rationalValues = new double[lengthArray];

            for(int i = 0; i < rationalValues.length; i++) {
                rationalValues[i] = (random.nextDouble());
                //System.out.print(rationalValues[i] + ", ");
            }
            //System.out.println(".");

            //SelectNuovo2 selectAlgorithm = new SelectNuovo2();
            //Select selectAlgorithm = new Select();

            LowerWeightedMedian algorithm = new LowerWeightedMedian();
            double medianSelect = algorithm.getLowerWeightedMedian(rationalValues);

            double medianQuickSort = quickSort.useProblem(rationalValues);

            String version = "New version";
            if(medianSelect == medianQuickSort) {
                System.out.print(counter + ") " + version + " OK - ");
            }
            else {
                canContinue = false;
                System.out.print(counter + ") " + version + " FALLITO - ");
            }
            System.out.println("Length array: " + rationalValues.length + " - Median Select: " + medianSelect + " - Median QuickSort: " + medianQuickSort);
            counter++;
        }
    }
}
