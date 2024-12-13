package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/VacationServlet")
public class VacationServlet extends HttpServlet {

    // H2 データベース接続文字列
    private static final String DB_URL = "jdbc:h2:~/test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month")) + 1; // 月は0から開始
        String date = request.getParameter("date");
        String reason = request.getParameter("reason");

        String vacationDate = year + "-" + month + "-" + date;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO Vacations (vacation_date, reason) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vacationDate);
            stmt.setString(2, reason);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("データベースエラー", e);
        }

        response.sendRedirect("CalendarServlet?year=" + year + "&month=" + (month - 1));
    }
}
