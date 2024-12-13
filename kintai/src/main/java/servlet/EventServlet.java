package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/eventServlet")
public class EventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // イベントを格納するためのマップ
    private Map<String, String> events = new HashMap<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータから年、月、日を取得
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));
        String date = request.getParameter("date");

        // 削除ボタンが押された場合
        if ("true".equals(request.getParameter("deleteEvent"))) {
            String eventKey = year + "-" + (month + 1) + "-" + date;
            events.remove(eventKey);  // イベントを削除
        }

        // イベントをリクエストに設定
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("date", date);
        request.setAttribute("events", events);

        // イベント表示用のJSPにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/event.jsp");
        dispatcher.forward(request, response);
    }

    // 他の必要なメソッド（POSTなど）も実装できます
}