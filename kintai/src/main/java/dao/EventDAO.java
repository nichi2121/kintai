package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventDAO {

    private static final String URL = "jdbc:h2:~/desktop/SQL/kintai"; // H2データベースのURL（ローカルファイル）
    private static final String USER = "sa"; // ユーザー名
    private static final String PASSWORD = ""; // パスワード

    public EventDAO() {
        try {
            // JDBCドライバのロード
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // イベントを追加するメソッド
    public void addEvent(String year, String month, String date, String eventDescription) throws SQLException {
        String sql = "INSERT INTO events (event_key, description) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String eventKey = year + "-" + month + "-" + date;
            ps.setString(1, eventKey);
            ps.setString(2, eventDescription);
            ps.executeUpdate();
        }
    }

    // イベントを取得するメソッド
    public String getEvent(String year, String month, String date) throws SQLException {
        String eventKey = year + "-" + month + "-" + date;
        String sql = "SELECT description FROM events WHERE event_key = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, eventKey);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("description");
                } else {
                    return "なし";
                }
            }
        }
    }

    // イベントを削除するメソッド
    public void deleteEvent(String year, String month, String date) throws SQLException {
        String eventKey = year + "-" + month + "-" + date;
        String sql = "DELETE FROM events WHERE event_key = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, eventKey);
            ps.executeUpdate();
        }
    }

    // 休暇申請を追加するメソッド
    public void addVacation(String year, String month, String date, String reason) throws SQLException {
        String sql = "INSERT INTO vacations (vacation_key, reason) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String vacationKey = year + "-" + month + "-" + date;
            ps.setString(1, vacationKey);
            ps.setString(2, reason);
            ps.executeUpdate();
        }
    }

    // 休暇申請を取得するメソッド
    public String getVacation(String year, String month, String date) throws SQLException {
        String vacationKey = year + "-" + month + "-" + date;
        String sql = "SELECT reason FROM vacations WHERE vacation_key = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, vacationKey);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("reason");
                } else {
                    return "なし";
                }
            }
        }
    }
}
