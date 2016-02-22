package com.mycompany.app;

import java.util.Random;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        System.out.println("-=Start=-");


        Matrix sm01 = smCreate(10, 10, 1000);
        Matrix sm02 = smCreate(10, 10, 1000);
sm01.print();
sm02.print();


        SparseMatrixSupport<Matrix> sparse_matrix_support = new SparseMatrixSupportImpl();
        Matrix sm03 = sparse_matrix_support.multiply(sm01, sm02);
sm03.print();


        Matrix sm04 = smCreate(10, 10, 1000);
        Matrix sm05 = sparse_matrix_support.fromStream(sparse_matrix_support.toStream(sm04));

sm05.print();

        System.out.println("-=Stop=-");


    }


//     генерация матрицы классическим способом - генерируем случайные значения на лету и тут же
    // скармливаем в качестве параметра методу Matrix.put

    static Matrix smCreate(int rowCount, int columnCount, int maxNotNullElements) {

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        создать матрицу конструктором типа  Matrix

        Matrix matrix = new Matrix(rowCount, columnCount);

//        получить рандомные значения
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < maxNotNullElements; i++) {
            matrix.put(random.nextInt(rowCount), random.nextInt(columnCount), random.nextInt(10));
        }

        System.out.println("Generation complete");
        return matrix;
    }


}
