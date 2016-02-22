package com.mycompany.app;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Andrei on 20.02.2016.
 */

public class Matrix {

    private final int i;
    private final int j;

    // основная структура данных для хранения разреженной матрицы
    private Map<Integer, Map<Integer, Integer>> rows;


    public Matrix(int i, int j) {
        this.i = i;
        this.j = j;
        rows = new HashMap<>(i);
    }


    //    основной метод заполениия матрицы значениями
    public void put(int i, int j, int value) {
//        проверка на null
        if (rows.get(i) == null) rows.put(i, new HashMap<>());

// если не нул то спокойно добавляем значение в коллекцию
        rows.get(i).put(j, value);


    }

    public Integer get(int i, int j) {
        return rows.get(i).get(j);
    }


    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Matrix dotProduct(Matrix first, Matrix second) {
        if (first.getI() != second.getJ())
            throw new IllegalArgumentException
                    ("Number of columns of the first matrix must be the same as number of rows of the second matrix");

//        транспонируем матрицу номер 2, а затем перемножим элементы строк на элементы строк
        Matrix second_t = second.mTrans();

        Matrix third = new Matrix(first.getI(), second.getJ());

        for (Integer idx_i : first.rows.keySet()) {
            for (Integer idx_j : second_t.rows.keySet()) {
                try {
                    third.put(idx_i, idx_j, vecProduct(rows.get(idx_i), second_t.rows.get(idx_j)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return third;

    }
// процедура транспонирования матрицы
    private Matrix mTrans() {
        Matrix trans = new Matrix(j, i);

        for (Integer idx_i: rows.keySet()) {
            for (Integer idx_j: rows.get(idx_i).keySet()) {
                trans.put(idx_j, idx_i, rows.get(idx_i).get(idx_j));
            }
        }
        return trans;

    }

//    процедура перемножения векторов
    private Integer vecProduct(Map<Integer, Integer> first_vec, Map<Integer, Integer> secon_vec) {
        if(Collections.disjoint(first_vec.keySet(), secon_vec.keySet()))
            return null;
        int ret = 0;

        for(Integer ind_j: first_vec.keySet()) {
            if(secon_vec.get(ind_j)==null)
                continue;
            ret += first_vec.get(ind_j) * secon_vec.get(ind_j);
        }
        return ret;

    }

    public void print() {
        System.out.println("The sparse matrix is ");
        for (int idx_i = 0; idx_i < i; idx_i++) {
            for (int idx_j = 0; idx_j < j; idx_j++) {
                System.out.print(get(idx_i, idx_j) + "\t");
            }
            System.out.println();
        }
    }

}
