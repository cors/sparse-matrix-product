import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by Andrei on 20.02.2016.
 */
public class SparseMatrixSupportImpl implements SparseMatrixSupport<Matrix> {


    public Stream<Integer> toStream(Matrix matrix) {
        Stream<Integer> targetStream = Stream.generate(new SparseMatrixSupplier(matrix));
        return targetStream.limit(  matrix.getTotalOfRows() * matrix.getTotalOfColumns() );
    }

    public Matrix fromStream(Stream<Integer> stream) {

        final Matrix[] matrix = {null};

        stream.forEach(new Consumer<Integer>() {
            long query = 0;
            int rowTotal, colTotal;
            int currentRow = 0;
            int currentCol = 0;

            @Override
            public void accept(Integer value) {
                query++;
                if (query == 1) {
                    rowTotal = value;
                    return;
                }
                if (query == 2) {
                    colTotal = value;
                    return;
                }
                if (query == 3) {
                    matrix[0] = new Matrix(rowTotal, colTotal);
                    if (currentCol == colTotal) {
                        matrix[0].put(currentRow++, currentCol, value);
                        currentCol = 0;
                    }
                    else {
                        matrix[0].put(currentRow, currentCol++, value);
                    }
                } else {
                    if (currentCol == colTotal) {
                        matrix[0].put(currentRow++, currentCol, value);
                        currentCol = 0;
                    }
                    else {
                        matrix[0].put(currentRow, currentCol++, value);
                    }

                }
            }

        });

        return matrix[0];
    }

    public Matrix multiply(Matrix first, Matrix second) {
        return first.matrixProduct(second);
    }
}
