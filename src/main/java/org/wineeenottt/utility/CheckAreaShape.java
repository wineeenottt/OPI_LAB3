package org.wineeenottt.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CheckAreaShape {

    private static final BigDecimal TWO = BigDecimal.valueOf(2);
    private static final BigDecimal ZERO = BigDecimal.ZERO;

    // 0 ≤ x ≤ R, -R/2 ≤ y ≤ 0
    public static boolean areaRectangle(BigDecimal x, BigDecimal r, BigDecimal y) {
        if (x.compareTo(ZERO) < 0 || x.compareTo(r) > 0) return false;
        BigDecimal halfR = r.divide(TWO, 10, RoundingMode.HALF_UP);
        return y.compareTo(halfR.negate()) >= 0 && y.compareTo(ZERO) <= 0;
    }

    // x ≥ 0, y ≥ 0, x² + y² ≤ (R/2)²
    public static boolean areaCircle(BigDecimal x, BigDecimal r, BigDecimal y) {
        if (x.compareTo(ZERO) < 0 || y.compareTo(ZERO) < 0) return false;
        BigDecimal halfR = r.divide(TWO, 10, RoundingMode.HALF_UP);
        BigDecimal radiusSquared = halfR.pow(2);
        BigDecimal xSq = x.pow(2);
        BigDecimal ySq = y.pow(2);
        return xSq.add(ySq).compareTo(radiusSquared) <= 0;
    }

    // x ≤ 0, y ≤ 0, и y ≥ (1/2)x - R/2  -  x + 2y + R ≥ 0
    public static boolean areaTriangle(BigDecimal x, BigDecimal r, BigDecimal y) {
        if (x.compareTo(ZERO) > 0 || y.compareTo(ZERO) > 0) return false;
        BigDecimal twoY = y.multiply(TWO);
        BigDecimal expr = x.add(twoY).add(r);
        return expr.compareTo(ZERO) >= 0;
    }

    public static boolean areaCheck(BigDecimal x, BigDecimal r, BigDecimal y) {
        return areaCircle(x, r, y) || areaRectangle(x, r, y) || areaTriangle(x, r, y);
    }
}