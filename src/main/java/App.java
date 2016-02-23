import java.util.Random;

/**
 */
public class App {
    public static void main(String[] args) {

        System.out.println("-------------------=Start=--------------------------");


        Matrix sm01 = smCreate(10, 10, 10);
        Matrix sm02 = smCreate(10, 10, 10);


        sm01.print();
        sm02.print();


        SparseMatrixSupport<Matrix> sparseMatrixSupport = new SparseMatrixSupportImpl();
        Matrix sm03 = sparseMatrixSupport.multiply(sm01, sm02);

        sm03.print();
//
//
//        Matrix sm04 = smCreate(10, 10, 1000);
//        Matrix sm05 = sparse_matrix_support.fromStream(sparse_matrix_support.toStream(sm04));
//
//sm05.print();

        System.out.println("-------------------=Stop=--------------------------");


    }


//     генерация матрицы классическим способом - генерируем случайные значения на лету и тут же
    // скармливаем в качестве параметра методу Matrix.put

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
