import org.junit.Assert;
import org.junit.Test;
import org.wineeenottt.utility.CoordValidation;

import java.math.BigDecimal;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class CoordValidationTest {

    @Test
    public void testValidX() {
        assertTrue(CoordValidation.checkX(new BigDecimal("0")));
        assertTrue(CoordValidation.checkX(new BigDecimal("3")));
        assertTrue(CoordValidation.checkX(new BigDecimal("-5")));
    }

    @Test
    public void testInvalidX() {
        assertFalse(CoordValidation.checkX(new BigDecimal("10")));
        assertFalse(CoordValidation.checkX(new BigDecimal("-6")));
    }

    @Test
    public void testNull() {
        assertFalse(CoordValidation.checkX(null));
    }
//    @Test
//    public void testFail() {
//        Assert.fail("Принудительное падение теста");
//    }
}