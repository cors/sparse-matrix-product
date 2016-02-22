package com.mycompany.app;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by Andrei on 20.02.2016.
 */
public class SparseMatrixSupportImpl implements SparseMatrixSupport<Matrix> {


    public Stream<Integer> toStream(Matrix matrix) {
        Stream<Integer> targetStream = Stream.generate(new SparseMatrixSupplier(matrix));
        return targetStream.limit( (long) matrix.getI() * (long) matrix.getJ() + 2L);
    }

    public Matrix fromStream(Stream<Integer> stream) {

        final Matrix[] matrix = {null};

        stream.forEach(new Consumer<Integer>() {
            long request = 0;
            int rowCount, columnCount;
            int currentRow = 0;
            int currentColumn = 0;

            @Override
            public void accept(Integer value) {
                request++;
                if (request == 1) {
                    rowCount = value;
                    return;
                }
                if (request == 2) {
                    columnCount = value;
                    return;
                }
                if (request == 3) {
                    matrix[0] = new Matrix(rowCount, columnCount);
                    put(value);
                } else {
                    put(value);
                }
            }

            public void put(Integer value) {
                if (currentColumn == columnCount - 1) {
                    matrix[0].put(currentRow++, currentColumn, value);
                    currentColumn = 0;
                } else matrix[0].put(currentRow, currentColumn++, value);
            }
        });

        return matrix[0];
    }

    public Matrix multiply(Matrix first, Matrix second) {
        return first.dotProduct(first, second);
    }
}
