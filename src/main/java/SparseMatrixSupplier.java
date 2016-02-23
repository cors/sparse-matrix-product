import java.util.function.Supplier;

/**
 * Created by Andrei on 21.02.2016.
 */
public class SparseMatrixSupplier implements Supplier<Integer> {

    final private Matrix matrix;
    final private int columnCount;
    final private int rowCount;
    private long request = 0;
    private int currentColumn = 0;
    private int currentRow = 0;

    SparseMatrixSupplier(Matrix matrix)
    {
        this.matrix = matrix;
        rowCount = matrix.getI();
        columnCount = matrix.getJ();
    }

    @Override
    public Integer get() {
        Integer value;
        request++;
        if(request == 1)
            return rowCount;
        if(request == 2)
            return  columnCount;

        if (currentColumn == columnCount - 1) {
            value = matrix.get(currentRow++, currentColumn);
            currentColumn = 0;
        }
        else
            value = matrix.get(currentRow,currentColumn++);
        return value;
    }

}