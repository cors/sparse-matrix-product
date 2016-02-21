package com.mycompany.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.*;

import org.apache.commons.collections.primitives.ArrayIntList;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();

        System.out.println("-=Start=-");


//        создать числовой ряд от 0 до 100 по аналогии с питоновским range
//        Stream<Integer> stream = IntStream.range(1, 100).boxed();

//      создать объект класса суппорт матриц для манипуляций с последними


        SparseMatrixSupport<M> sparse_matrix_support = new SparseMatrixSupportImpl();
        M mtrx1 = generateSparseMatrix(10, 10, 1000);
        M mtrx2 = generateSparseMatrix(10, 10, 1000);
//        mtrx1.print();
//        mtrx2.print();
        M mtrx3 = sparse_matrix_support.multiply(mtrx1, mtrx2);
//        mtrx3.print();
        M mtrx4 = generateSparseMatrix(10, 10, 1000);
        M mtrx5 = sparse_matrix_support.fromStream(sparse_matrix_support.toStream(mtrx4));


        System.out.println("-=Stop=-");


//      перегнать range в массив целых чисел

//        M matrix01 = generateSparseMatrix(5, 10, 1000);



        long t2 = System.currentTimeMillis();
        System.out.println("Execution time: " + (t2 - t1) / 1000 + " sec ");
    }


//     генерация матрицы классическим способом - генерируем случайные значения на лету и тут же
    // скармливаем в качестве параметра методу M.put

    static M generateSparseMatrix(int rowCount, int columnCount, int maxNotNullElements) {

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        создать матрицу конструктором типа  M

        M matrix = new M(rowCount, columnCount);

//        получить рандомные значения
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < maxNotNullElements; i++) {
            matrix.put(random.nextInt(rowCount), random.nextInt(columnCount), random.nextInt(10));
        }

        System.out.println("Generation complete");
        return matrix;
    }


}
