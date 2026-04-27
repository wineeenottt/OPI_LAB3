package org.wineeenottt.db;

import org.wineeenottt.model.Point;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DataBaseManager {

    @Resource(lookup = "java:jboss/datasources/PostgresDS")
    private DataSource ds;

    public void insertPoint(Point point) throws SQLException {
        String sql = "INSERT INTO results (x, y, r, exec_time, cur_time, hit) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBigDecimal(1, point.getX());
            ps.setBigDecimal(2, point.getY());
            ps.setBigDecimal(3, point.getR());
            ps.setLong(4, point.getExecTime());
            ps.setTimestamp(5, Timestamp.valueOf(point.getCurTime()));
            ps.setBoolean(7, point.isHit());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearTable() throws SQLException {
        String sql = "TRUNCATE TABLE results RESTART IDENTITY";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Point> loadPoints() throws SQLException {
        String sql = "SELECT x, y, r, exec_time, cur_time, hit FROM results";
        List<Point> points = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Point point = new Point(
                        rs.getBigDecimal("x"),
                        rs.getBigDecimal("y"),
                        rs.getBigDecimal("r"),
                        rs.getLong("exec_time"),
                        rs.getTimestamp("cur_time").toLocalDateTime(),
                        rs.getBoolean("hit")
                );
                points.add(point);
            }
        }
        return points;
    }
}
