import gnu.trove.map.TIntIntMap;
import gnu.trove.map.hash.TIntIntHashMap;
import gnu.trove.map.hash.TIntObjectHashMap;


/**
 * Created by Andrei on 20.02.2016.
 */

public class Matrix {

    private final int totalOfRows; //rowsTotal
    private final int totalOfColumns; //columnsTotal

    private final TIntObjectHashMap<TIntIntHashMap> rows;
    private final TIntObjectHashMap<TIntIntHashMap> columns;

    public boolean equalsMatrix(Matrix second) {
        for (int row = 0; row < totalOfRows; row++) {
            for (int col = 0; col < totalOfColumns; col++) {
                if (second.get(row, col) != this.get(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix(int totalOfRows, int totalOfColumns) {
        this.totalOfRows = totalOfRows;
        this.totalOfColumns = totalOfColumns;
        this.rows = new TIntObjectHashMap<>();
        this.columns = new TIntObjectHashMap<>();
    }

    public void put(int rowIndex, int colIndex, int value) {
        if (value == 0) {
            return;
        }

        TIntIntHashMap row = rows.get(rowIndex);
        if (row == null) {
            row = new TIntIntHashMap();
            rows.put(rowIndex, row);
        }
        row.put(colIndex, value);

        TIntIntHashMap col = columns.get(colIndex);
        if (col == null) {
            col = new TIntIntHashMap();
            columns.put(colIndex, col);
        }
        col.put(rowIndex, value);
    }

    public int get(int rowIndex, int colIndex) {
        TIntIntHashMap row = rows.get(rowIndex);
        if (row == null)
            return 0;
        return row.get(colIndex);
    }


    public int getTotalOfRows() {
        return totalOfRows;
    }

    public int getTotalOfColumns() {
        return totalOfColumns;
    }

    public Matrix matrixProduct(Matrix m) {
        if (getTotalOfColumns() != m.getTotalOfRows())
            throw new IllegalArgumentException("Incorrect operation: total columns of matrix first <> total rows of matrix m");

        Matrix result = new Matrix(getTotalOfRows(), m.getTotalOfColumns());

        for (int rowIndex : rows.keys()) {
            TIntIntHashMap row = rows.get(rowIndex);
            for (int colIndex : m.columns.keys()) {
                TIntIntHashMap col = m.columns.get(colIndex);
                int value = dotProduct(row, col);
                result.put(rowIndex, colIndex, value);
            }
        }

        return result;
    }

    private static int dotProduct(TIntIntMap v, TIntIntMap u) {
        int value = 0;
        for (int j : v.keys()) {
            value += v.get(j) * u.get(j);
        }
        return value;
    }

    public void print() {
        System.out.println("-----------------------------------------------");
        for (int rowIndex = 0; rowIndex < totalOfRows; rowIndex++) {
            for (int colIndex = 0; colIndex < totalOfColumns; colIndex++) {
                System.out.print(get(rowIndex, colIndex) + "\t");
            }
            System.out.println();
        }
    }

}