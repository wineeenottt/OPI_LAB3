package org.wineeenottt.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.wineeenottt.model.Point;
import org.wineeenottt.utility.CheckAreaShape;
import org.wineeenottt.utility.CoordValidation;
import org.wineeenottt.db.DataBaseManager;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Named("controllerBean")
@SessionScoped
public class ControllerBean implements Serializable {
    private static final Logger logger = Logger.getLogger(ControllerBean.class.getName());

    @Inject
    private PointsBean pointsBean;

    @Inject
    private ReceiptCoordBean receiptCoordBean;

    @Inject
    private DataBaseManager dbManager;

    public void addPoint() {
        BigDecimal x = receiptCoordBean.getClickX() != null ? receiptCoordBean.getClickX() : receiptCoordBean.getX();
        BigDecimal y = receiptCoordBean.getClickY() != null ? receiptCoordBean.getClickY() : receiptCoordBean.getY();
        BigDecimal r = receiptCoordBean.getR();
        logger.info("addPoint: x = " + x + ", y = " + y + ", r = " + r);

        long start = System.nanoTime();

        if (!validateInput(x, y, r)) return;

        boolean hit = CheckAreaShape.areaCheck(x, r, y);
        LocalDateTime curTime = LocalDateTime.now();
        long execTime = ((System.nanoTime() - start) / 1_000);

        Point point = new Point(x, y, r, execTime, curTime, hit);
        pointsBean.addPoint(point);

        receiptCoordBean.setX(null);
        receiptCoordBean.setY(null);
        receiptCoordBean.setClickX(null);
        receiptCoordBean.setClickY(null);

    }

    public void clearPoint() {
        pointsBean.clearPoints();
        receiptCoordBean.setX(null);
        receiptCoordBean.setY(null);
        receiptCoordBean.setR(null);
        receiptCoordBean.setClickX(null);
        receiptCoordBean.setClickY(null);
    }

    private boolean validateInput(BigDecimal x, BigDecimal y, BigDecimal r) {
        FacesContext context = FacesContext.getCurrentInstance();

        if (x == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка X", "Поле X не выбрано"));
            return false;
        }
        if (!CoordValidation.checkX(x)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка X", "X вне диапазона [-5, 3]"));
            return false;
        }

        if (y == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка Y", "Поле Y не заполнено"));
            return false;
        }
        if (!CoordValidation.checkY(y)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка Y", "Y вне диапазона [-5, 5]"));
            return false;
        }

        if (r == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка R", "Поле R не выбрано"));
            return false;
        }
        if (!CoordValidation.checkR(r)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка R", "R вне диапазона [1, 5]"));
            return false;
        }
        return true;
    }
}
