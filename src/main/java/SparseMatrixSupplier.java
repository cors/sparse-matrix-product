import java.util.function.Supplier;

/**
 * Created by Andrei on 21.02.2016.
 */
public class SparseMatrixSupplier implements Supplier<Integer> {

    final private Matrix matrix;
    final private int colTotal;
    final private int rowTotal;
    private long request = 0;
    private int currentCol = 0;
    private int currentRow = 0;

    SparseMatrixSupplier(Matrix matrix)
    {
        this.matrix = matrix;
        rowTotal = matrix.getTotalOfRows();
        colTotal = matrix.getTotalOfColumns();
    }

    @Override
    public Integer get() {
        Integer value;
        request++;
        if(request == 1)
            return rowTotal;
        if(request == 2)
            return  colTotal;

        if (currentCol == colTotal - 1) {
            value = matrix.get(currentRow++, currentCol);
            currentCol = 0;
        }
        else
            value = matrix.get(currentRow,currentCol++);
        return value;
    }

}