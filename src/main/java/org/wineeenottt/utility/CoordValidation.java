package org.wineeenottt.utility;


import java.math.BigDecimal;

public class CoordValidation {
    static final BigDecimal MAX_X = new BigDecimal("3");
    static final BigDecimal MIN_X = new BigDecimal("-5");

    static final BigDecimal MAX_Y = new BigDecimal("5");
    static final BigDecimal MIN_Y = new BigDecimal("-5");

    static final BigDecimal MAX_R = new BigDecimal("5");
    static final BigDecimal MIN_R = BigDecimal.ONE;

    public static boolean checkX(BigDecimal x) {
        return x != null && x.compareTo(MIN_X) >= 0 && x.compareTo(MAX_X) <= 0;
    }

    public static boolean checkY(BigDecimal y) {
        return y != null && y.compareTo(MIN_Y) >= 0 && y.compareTo(MAX_Y) <= 0;
    }

    public static boolean checkR(BigDecimal r) {
        return r != null && r.compareTo(MIN_R) >= 0 && r.compareTo(MAX_R) <= 0;
    }
}
