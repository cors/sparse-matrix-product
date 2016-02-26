import gnu.trove.map.hash.TIntIntHashMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.set.TIntSet;

import java.util.Map;


/**
 * Created by Andrei on 20.02.2016.
 */

public class Matrix {

    private final int totalOfRows; //rowsTotal
    private final int totalOfColumns; //columnsTotal

//    private Map<Integer, Map<Integer, Integer>> rows;

    private TIntObjectHashMap<TIntIntHashMap> rows;

    public Matrix(int totalOfRows, int totalOfColumns) {
        this.totalOfRows = totalOfRows;
        this.totalOfColumns = totalOfColumns;
        rows = null;
    }

    public void put(int rowOfMatrix, int colOfMatrix, int value) {


        if (value == 0) {
            return;
        }

        if (rows == null) {
            rows = new TIntObjectHashMap<>();
        }

        TIntIntHashMap columnAndValue = null;
        int[] colArray = {colOfMatrix};
        int[] valArray = {value};

        if (rows.get(rowOfMatrix) == null) {
            columnAndValue = new TIntIntHashMap(colArray, valArray);
            rows.put(rowOfMatrix, columnAndValue);
        }

        TIntIntHashMap valIntInt = rows.get(rowOfMatrix);
        int put = valIntInt.put(colOfMatrix, value);
    }

    public int get(int rowOfMatrix, int colOfMatrix) {
        if (rows.get(rowOfMatrix) == null)
            return 0;

        return rows.get(rowOfMatrix).get(colOfMatrix);
    }


    public int getTotalOfRows() {
        return totalOfRows;
    }

    public int getTotalOfColumns() {
        return totalOfColumns;
    }

    public Matrix matrixProduct(Matrix second) {
        if (second.getTotalOfColumns() != getTotalOfRows())
            throw new IllegalArgumentException
                    ("Incorrect operation: total rows of matrix first <> total column of matrix second");

        Matrix secondMatrixTrans = second.mTrans();

        Matrix targetMatrix = new Matrix(getTotalOfRows(), second.getTotalOfColumns());

        int[] keysCurrent = keySet();
        int[] keysSecondTrans = secondMatrixTrans.keySet();
        int result = 0;

        for (int i = 0; i < keysCurrent.length; i++) {
            int rowIndex = keysCurrent[i];


            for (int j = 0; j < keysSecondTrans.length; j++) {
                int colIndex = keysSecondTrans[j];

                result += get(rowIndex, colIndex) * secondMatrixTrans.get(rowIndex, colIndex);

                targetMatrix.put(rowIndex, colIndex, result);

            }
        }


        return targetMatrix;
    }

    public int[] keySet() {
        return rows.keys();
    }

//    public Integer vecProduct(TIntIntHashMap first_vec, TIntIntHashMap second_vec) {
//
//
//        int result = 0;
//        for (Integer idx_j : first_vec.keySet()) {
//            if (second_vec.get(idx_j) == null)
//                continue;
//            result += first_vec.get(idx_j) * second_vec.get(idx_j);
//        }
//
//        return result;
//
//
//    }

    private Matrix mTrans() {
        Matrix trans = new Matrix(totalOfColumns, totalOfRows);

//        for (Integer idx_i : rows.keySet()) {
//            for (Integer idx_j : rows.get(idx_i).keySet()) {
//                trans.put(idx_j, idx_i, rows.get(idx_i).get(idx_j));
//            }
//        }
        return this;

    }

    public void print() {
        System.out.println("-----------------------------------------------");
        for (int idx_i = 0; idx_i < totalOfRows; idx_i++) {
            for (int idx_j = 0; idx_j < totalOfColumns; idx_j++) {
                System.out.print(get(idx_i, idx_j) + "\t");
            }
            System.out.println();
        }
    }

}
