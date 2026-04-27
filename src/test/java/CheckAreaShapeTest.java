import org.junit.Test;
import org.wineeenottt.utility.CheckAreaShape;

import java.math.BigDecimal;

import static org.junit.Assert.*;


public class CheckAreaShapeTest {

    @Test
    public void rectangleHit() {
        assertTrue(CheckAreaShape.areaRectangle(
                new BigDecimal("1"),
                new BigDecimal("4"),
                new BigDecimal("-1")
        ));
    }

    @Test
    public void circleMiss() {
        assertFalse(CheckAreaShape.areaCircle(
                new BigDecimal("5"),
                new BigDecimal("5"),
                new BigDecimal("5")
        ));
    }

    @Test
    public void triangleHit() {
        assertTrue(CheckAreaShape.areaTriangle(
                new BigDecimal("-1"),
                new BigDecimal("4"),
                new BigDecimal("-1")
        ));
    }

    @Test
    public void fullCheck() {
        assertTrue(CheckAreaShape.areaCheck(
                new BigDecimal("1"),
                new BigDecimal("4"),
                new BigDecimal("-1")
        ));
    }
}