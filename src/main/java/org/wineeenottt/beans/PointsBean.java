package org.wineeenottt.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.wineeenottt.model.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.wineeenottt.db.DataBaseManager;

import java.sql.SQLException;
import java.util.logging.Logger;

@Named("pointsBean")
@SessionScoped
public class PointsBean implements Serializable {
    private static final Logger logger = Logger.getLogger(PointsBean.class.getName());

    private List<Point> points = new ArrayList<>();

    @Inject
    private DataBaseManager dbManager;

    @Inject
    private ReceiptCoordBean receiptCoordBean;

    @PostConstruct
    public void init() {
        loadPointsFromDB();
    }

    public List<Point> getPoints() {
        return points;
    }

    public void addPoint(Point point) {
        points.add(point);
        try {
            dbManager.insertPoint(point);
            logger.severe("готово ");
        } catch (SQLException e) {
            logger.severe("гелиос умер " + e.getMessage());
        }
    }

    public void clearPoints() {
        points.clear();
        try {
            dbManager.clearTable();
            logger.severe("готово ");
        } catch (SQLException e) {
            logger.severe("гелиос умер " + e.getMessage());
        }
    }

    public void loadPointsFromDB() {
        try {
            points = dbManager.loadPoints();
            logger.severe("готово ");
        } catch (SQLException e) {
            logger.severe(" гелиос умер " + e.getMessage());
        }
    }
}
