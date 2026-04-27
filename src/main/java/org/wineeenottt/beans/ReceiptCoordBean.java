package org.wineeenottt.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@Named("receiptCoordBean")
@SessionScoped
public class ReceiptCoordBean implements Serializable {

    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal r;

    private BigDecimal clickX;
    private BigDecimal clickY;

    @Inject
    private PointsBean pointsBean;

    @PostConstruct
    public void init() {
        if (r == null && pointsBean.getPoints() != null && !pointsBean.getPoints().isEmpty()) {
            r = new  BigDecimal(5);
        }
    }

    public BigDecimal getClickX() {
        return clickX;
    }

    public void setClickX(BigDecimal clickX) {
        this.clickX = clickX;
    }

    public BigDecimal getClickY() {
        return clickY;
    }

    public void setClickY(BigDecimal clickY) {
        this.clickY = clickY;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public BigDecimal getR() {
        return r;
    }

    public void setR(BigDecimal r) {
        this.r = r;
    }

    public String getRFormatted() {
        return r != null ? format(r) : "R";
    }

    public String getHalfRFormatted() {
        return r != null ? format(r.divide(BigDecimal.valueOf(2), 10, RoundingMode.HALF_UP)) : "R/2";
    }

    public String getNegRFormatted() {
        return r != null ? format(r.negate()) : "-R";
    }

    public String getNegHalfRFormatted() {
        return r != null ? format(r.divide(BigDecimal.valueOf(2), 10, RoundingMode.HALF_UP).negate()) : "-R/2";
    }

    private String format(BigDecimal v) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(v);
    }
}
