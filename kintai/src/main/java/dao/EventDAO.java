package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventDAO {

    private static final String URL = "jdbc:h2:~/SQL/kintai";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final Logger logger = Logger.getLogger(EventDAO.class.getName());

    public EventDAO() {
        try {
            Class.forName("org.h2.Driver");
            createTableIfNotExists();
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "JDBC Driverのロードに失敗しました", e);
            throw new RuntimeException("JDBC Driverロードに失敗しました", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "データベースの初期化に失敗しました", e);
            throw new RuntimeException("データベースの初期化に失敗しました", e);
        }
    }

    public void addVacation(String year, String month, String date, String reason) throws SQLException {
        String vacationKey = year + "-" + month + "-" + date;
        String insertSql = "INSERT INTO vacations (vacation_key, reason) VALUES (?, ?);";

        logger.info("休暇申請追加 - キー: " + vacationKey + ", 理由: " + reason);  // デバッグログ

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, vacationKey);
                insertStmt.setString(2, reason);
                int rowsAffected = insertStmt.executeUpdate();
                logger.info("休暇申請が追加されました: " + rowsAffected + " 行");
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                logger.log(Level.SEVERE, "休暇申請追加に失敗しました", e);
                throw e;
            }
        }
    }

    private void createTableIfNotExists() throws SQLException {
        String eventSql = "CREATE TABLE IF NOT EXISTS events (event_key VARCHAR(20) PRIMARY KEY, description VARCHAR(255));";
        String vacationSql = "CREATE TABLE IF NOT EXISTS vacations (vacation_key VARCHAR(20) PRIMARY KEY, reason VARCHAR(255));";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement createEventStmt = conn.prepareStatement(eventSql);
             PreparedStatement createVacationStmt = conn.prepareStatement(vacationSql)) {

            createEventStmt.execute();
            createVacationStmt.execute();
            logger.info("テーブルが作成または既に存在しています");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "テーブル作成に失敗しました", e);
            throw e;
        }
    }
}
