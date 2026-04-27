package org.wineeenottt.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Point implements Serializable {
    private BigDecimal x, y, r;
    private long execTime;
    private LocalDateTime curTime;
    private boolean hit;

    public Point(BigDecimal x, BigDecimal y, BigDecimal r, long execTime, LocalDateTime curTime, boolean hit) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.execTime = execTime;
        this.curTime = curTime;
        this.hit = hit;
    }


    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    public BigDecimal getR() {
        return r;
    }

    public long getExecTime() {
        return execTime;
    }

    public LocalDateTime getCurTime() {
        return curTime;
    }

    public boolean isHit() {
        return hit;
    }

    public long getCurTimestamp() {
        return curTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
