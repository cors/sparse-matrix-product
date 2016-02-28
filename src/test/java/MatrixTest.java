import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Andrei on 26.02.2016.
 */
public class MatrixTest {

     @Test
     public void create() {
         Matrix m = new Matrix(2, 2);
         m.put(0, 0, 1);
         m.put(0, 1, 1);
         m.put(1, 1, 1);

         assertEquals(1, m.get(0, 0));
         assertEquals(1, m.get(0, 1));
         assertEquals(0, m.get(1, 0));
         assertEquals(1, m.get(1, 1));
     }

     @Test
     public void multiplyByIdentity() {
         Matrix m = new Matrix(2, 2);
         m.put(0, 0, 1);
         m.put(0, 1, 2);
         m.put(1, 1, 3);

         Matrix b = new Matrix(2, 2);
         b.put(0, 0, 1);
         b.put(1, 1, 1);

         Matrix a = m.matrixProduct(b);

         assertEquals(1, a.get(0, 0));
         assertEquals(2, a.get(0, 1));
         assertEquals(0, a.get(1, 0));
         assertEquals(3, a.get(1, 1));
     }

     @Test
     public void multiplyByZero() {
         Matrix m = new Matrix(2, 2);
         m.put(0, 0, 1);
         m.put(0, 1, 2);
         m.put(1, 1, 3);

         Matrix b = new Matrix(2, 2);

         Matrix a = m.matrixProduct(b);

         assertEquals(0, a.get(0, 0));
         assertEquals(0, a.get(0, 1));
         assertEquals(0, a.get(1, 0));
         assertEquals(0, a.get(1, 1));
     }

     @Test
     public void multiply() {
         //  1 2
         // -1 0
         Matrix m = new Matrix(2, 2);
         m.put(0, 0, 1);
         m.put(0, 1, 2);
         m.put(1, 0, -1);

         // -1
         // -1
         Matrix b = new Matrix(2, 1);
         b.put(0, 0, 1);
         b.put(1, 0, -1);

         // -1
         // -1
         Matrix a = m.matrixProduct(b);

         assertEquals(-1, a.get(0, 0));
         assertEquals(-1, a.get(1, 0));
     }

     @Test
     public void equalMatrix() {
         Matrix m = new Matrix(2, 2);
         m.put(0, 0, 1);
         m.put(0, 1, 2);
         m.put(1, 0, -1);

         Matrix b = new Matrix(2, 2);
         b.put(0, 0, 1);
         b.put(0, 1, 2);
         b.put(1, 0, -1);

         boolean eq = m.equalsMatrix(b);
         assertTrue(eq);

     }

    @Test
    public void fromToStream() {

//        Matrix sm01 = App.smCreate(10, 5, 10);
//        Matrix sm02 = App.smCreate(5, 10, 10);
        Matrix sm01 = App.smCreate(10000, 10000, 10000);
        // Matrix sm02 = App.smCreate(1000, 10000, 100);

        SparseMatrixSupport<Matrix> sparseMatrixSupport = new SparseMatrixSupportImpl();
        // Matrix sm03 = sparseMatrixSupport.multiply(sm01, sm02);
//        sm03.print();

        // to Stream
        Stream<Integer> integerStream = sparseMatrixSupport.toStream(sm01);

        // from Stream
        Matrix sm02 = sparseMatrixSupport.fromStream(integerStream);
//        sm03.print();
//        sm05.print();

        boolean eq = sm01.equalsMatrix(sm02);
        assertTrue(eq);


    }
}

