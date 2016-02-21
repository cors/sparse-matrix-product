package com.mycompany.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.primitives.*;

/**
 * Created by Andrei on 20.02.2016.
 */

public class M {

//    private ArrayList<IntList> values;
//    private ArrayList<IntList> rows;
//    private ArrayList<IntList> cols;

    private final int i;
    private final int j;

    // �������� ��������� ������ ��� �������� ����������� �������
    private Map<Integer, Map<Integer, Integer>> rows;


    public M(int i, int j) {
        this.i = i;
        this.j = j;
        rows = new HashMap<>(i);
    }


    //    �������� ����� ���������� ������� ����������
    public void put(int i, int j, int value) {
//        �������� �� null
        if (rows.get(i) == null) rows.put(i, new HashMap<>());

// ���� �� ��� �� �������� ��������� �������� � ���������
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

    public M dotProduct(M first, M second) {
        if (first.getI() != second.getJ())
            throw new IllegalArgumentException
                    ("Number of columns of the first matrix must be the same as number of rows of the second matrix");

//        ������������� ������� ����� 2, � ����� ���������� �������� ����� �� �������� �����
        M second_t = second.mTrans();

        M third = new M(first.getI(), second.getJ());

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
// ��������� ���������������� �������
    private M mTrans() {
        M trans = new M(j, i);

        for (Integer idx_i: rows.keySet()) {
            for (Integer idx_j: rows.get(idx_i).keySet()) {
                trans.put(idx_j, idx_i, rows.get(idx_i).get(idx_j));
            }
        }
        return trans;

    }

//    ��������� ������������ ��������
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
}
