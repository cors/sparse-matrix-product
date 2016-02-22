package com.mycompany.app;

import java.util.Random;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        System.out.println("-=Start=-");


//        создать числовой ряд от 0 до 100 по аналогии с питоновским range
//        Stream<Integer> stream = IntStream.range(1, 100).boxed();

//      создать объект класса суппорт матриц для манипуляций с последними


        M sm01 = smCreate(10, 10, 1000);
        M sm02 = smCreate(10, 10, 1000);
sm01.print();
sm02.print();


        SparseMatrixSupport<M> sparse_matrix_support = new SparseMatrixSupportImpl();
        M sm03 = sparse_matrix_support.multiply(sm01, sm02);
sm03.print();


        M sm04 = smCreate(10, 10, 1000);
        M sm05 = sparse_matrix_support.fromStream(sparse_matrix_support.toStream(sm04));

sm05.print();

        System.out.println("-=Stop=-");


    }


//     генерация матрицы классическим способом - генерируем случайные значения на лету и тут же
    // скармливаем в качестве параметра методу M.put

    static M smCreate(int rowCount, int columnCount, int maxNotNullElements) {

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
