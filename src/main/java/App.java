import java.util.Random;
import java.util.stream.Stream;

/**
 */
public class App {
    public static void main(String[] args) {

        int rowsMax = 1000000;
        int colMax = 1000000;

        Matrix m = new Matrix(rowsMax, colMax);
        Matrix b = new Matrix(rowsMax, colMax);

        Random random = new Random(System.currentTimeMillis());

        int notNullTotal = 2000;
        int Low = 1;
        int High = notNullTotal;


        for (int i = 0; i < notNullTotal; i++) {
            System.out.println(i);

            m.put(random.nextInt(rowsMax), random.nextInt(colMax), random.nextInt(High - Low) + Low);
            b.put(random.nextInt(rowsMax), random.nextInt(colMax), random.nextInt(High - Low) + Low);
        }

        Matrix target = m.matrixProduct(b);

//        target.print();

        System.out.println("----------------------------------------------------------");


    }

    static Matrix smCreate(int rowsTotal, int colTotal, int notNullTotal) {

        Matrix matrix = new Matrix(rowsTotal, colTotal);

        Random random = new Random(System.currentTimeMillis());

        int Low = 1;
        int High = notNullTotal;

        for (int i = 0; i < notNullTotal; i++) {
            matrix.put(random.nextInt(rowsTotal), random.nextInt(colTotal), random.nextInt(High - Low) + Low);
        }

        return matrix;
    }


}
