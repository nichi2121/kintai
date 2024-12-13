package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventDAO {

    private static final String URL = "jdbc:h2:~/SQL/kintai"; // 修正版のH2データベースURL
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final Logger logger = Logger.getLogger(EventDAO.class.getName());

    public EventDAO() {
        try {
            Class.forName("org.h2.Driver");
            // 初期化時にテーブル作成
            createTableIfNotExists();
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "JDBC Driverのロードに失敗しました", e);
            throw new RuntimeException("JDBC Driverロードに失敗しました", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "データベースの初期化に失敗しました", e);
            throw new RuntimeException("データベースの初期化に失敗しました", e);
        }
    }

    // イベントを追加するメソッド
    public void addEvent(String year, String month, String date, String eventDescription) throws SQLException {
        String insertSql = "INSERT INTO events (event_key, description) VALUES (?, ?);";
        String eventKey = year + "-" + month + "-" + date;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);  // トランザクション開始

            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, eventKey);
                insertStmt.setString(2, eventDescription);
                int rowsAffected = insertStmt.executeUpdate();
                logger.info("イベントが追加されました: " + rowsAffected + " 行");

                conn.commit();  // コミット
            } catch (SQLException e) {
                conn.rollback();  // エラー時にロールバック
                logger.log(Level.SEVERE, "イベント追加に失敗しました", e);
                throw e;
            }
        }
    }

    // イベントを取得するメソッド
    public String getEvent(String year, String month, String date) throws SQLException {
        String eventKey = year + "-" + month + "-" + date;
        String sql = "SELECT description FROM events WHERE event_key = ?;";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, eventKey);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("description");
                }
            }
        }
        return "なし";  // イベントが見つからなかった場合
    }

    // イベントを削除するメソッド
    public void deleteEvent(String year, String month, String date) throws SQLException {
        String eventKey = year + "-" + month + "-" + date;
        String sql = "DELETE FROM events WHERE event_key = ?;";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, eventKey);
            int rowsAffected = ps.executeUpdate();
            logger.info("削除されたイベント数: " + rowsAffected);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "イベント削除に失敗しました", e);
            throw e;
        }
    }

    // テーブルが存在しない場合に作成するメソッド
    private void createTableIfNotExists() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS events (event_key VARCHAR(20) PRIMARY KEY, description VARCHAR(255));";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement createStmt = conn.prepareStatement(sql)) {
            createStmt.execute();
            logger.info("テーブルが作成または既に存在しています");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "テーブル作成に失敗しました", e);
            throw e;
        }
    }
}
