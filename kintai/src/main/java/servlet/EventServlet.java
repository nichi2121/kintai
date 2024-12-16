package servlet;

import java.io.IOException;
import java.sql.SQLException;

import dao.EventDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/eventServlet")
public class EventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // DAOインスタンス
    private EventDAO eventDAO = new EventDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータから年、月、日を取得
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));
        String date = request.getParameter("date");

        // イベントの追加
        String eventDescription = request.getParameter("event");
        if (eventDescription != null && !eventDescription.isEmpty()) {
            try {
                eventDAO.addEvent(String.valueOf(year), String.valueOf(month + 1), date, eventDescription);
            } catch (SQLException e) {
                e.printStackTrace(); // エラーハンドリング
            }
        }

        // 休暇申請の追加
        String vacationReason = request.getParameter("reason");
        if (vacationReason != null && !vacationReason.isEmpty()) {
            try {
                eventDAO.addVacation(String.valueOf(year), String.valueOf(month + 1), date, vacationReason);
            } catch (SQLException e) {
                e.printStackTrace(); // エラーハンドリング
            }
        }

        // 削除ボタンが押された場合
        if ("true".equals(request.getParameter("deleteEvent"))) {
            try {
                eventDAO.deleteEvent(String.valueOf(year), String.valueOf(month + 1), date);
            } catch (SQLException e) {
                e.printStackTrace(); // エラーハンドリング
            }
        }

        // イベントと休暇申請を取得
        String event = null;
        String vacation = null;
        try {
            event = eventDAO.getEvent(String.valueOf(year), String.valueOf(month + 1), date);
            vacation = eventDAO.getVacation(String.valueOf(year), String.valueOf(month + 1), date);
        } catch (SQLException e) {
            e.printStackTrace(); // エラーハンドリング
        }

        // リクエストにイベントと休暇申請情報をセット
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("date", date);
        request.setAttribute("event", event);
        request.setAttribute("vacation", vacation);

        // イベント表示用のJSPにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/event.jsp");
        dispatcher.forward(request, response);
    }
}
