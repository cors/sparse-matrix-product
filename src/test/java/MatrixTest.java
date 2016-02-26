import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Andrei on 26.02.2016.
 */
public class MatrixTest {

    @Test
    public void testPut() throws Exception {

        Matrix matrix = new Matrix(1000000, 1000000);

        Random random = new Random(System.currentTimeMillis());
        int notNullTotal = 1000;
        int rowsMax   = 1000000;
        int colMax    = 1000000;

        int Low = 1;
        int High = notNullTotal;

        for (int i = 0; i < notNullTotal; i++) {
            matrix.put(random.nextInt(rowsMax), random.nextInt(colMax), random.nextInt(High - Low) + Low);
        }


        Matrix target = matrix.matrixProduct(matrix);
        assertEquals(target, target);

    }

    @Test
    public void testGet() throws Exception {


    }

    @Test
    public void testGetTotalOfRows() throws Exception {

    }

    @Test
    public void testGetTotalOfColumns() throws Exception {

    }

    @Test
    public void testMatrixProduct() throws Exception {

    }

    @Test
    public void testVecProduct() throws Exception {

    }

    @Test
    public void testPrint() throws Exception {

    }
}