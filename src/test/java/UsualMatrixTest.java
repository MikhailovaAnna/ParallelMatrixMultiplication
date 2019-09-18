import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UsualMatrixTest {
    @Test
    void product() {
        UsualMatrix E = new UsualMatrix(2,2);
        E.setElement(0,0, 1);
        E.setElement(1,1, 1);
        UsualMatrix A = new UsualMatrix(2,2);
        A.setElement(0,0,5);
        A.setElement(0,1,9);
        A.setElement(1,0,17);
        A.setElement(1,1,3);
        UsualMatrix B = new UsualMatrix(A);
        A.product(E);
        assertEquals(A, B);
    }
}