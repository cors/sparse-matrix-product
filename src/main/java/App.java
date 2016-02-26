import java.util.Random;
import java.util.stream.Stream;

/**
 */
public class App {
    public static void main(String[] args) {

        Matrix sm01 = smCreate(10, 5, 10);
        Matrix sm02 = smCreate(5, 10, 10);
//        Matrix sm01 = smCreate(1000000, 1000000, 1000);
//        Matrix sm02 = smCreate(1000000, 1000000, 1000);


        sm01.print();
        sm02.print();


        SparseMatrixSupport<Matrix> sparseMatrixSupport = new SparseMatrixSupportImpl();
        Matrix sm03 = sparseMatrixSupport.multiply(sm01, sm02);

        sm03.print();
//
//
        Matrix sm04 = smCreate(10, 10, 10);
        sm04.print();

        // to Stream
        Stream<Integer> integerStream = sparseMatrixSupport.toStream(sm04);

        // from Stream
        Matrix sm05 = sparseMatrixSupport.fromStream(integerStream);

        sm05.print();

        System.out.println("End----------------------------------------------------------");


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
