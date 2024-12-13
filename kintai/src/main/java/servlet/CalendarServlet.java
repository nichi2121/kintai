package servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
    private Map<String, Boolean> vacations = new HashMap<>();  // 休暇情報の保存
    private Map<String, String> events = new HashMap<>();  // イベント情報の保存

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String yearParam = request.getParameter("year");
        String monthParam = request.getParameter("month");
        String dateParam = request.getParameter("date");
        String eventParam = request.getParameter("event");
        String vacationParam = request.getParameter("vacation");  // 休暇申請のパラメータ

        int year = (yearParam != null) ? Integer.parseInt(yearParam) : Calendar.getInstance().get(Calendar.YEAR);
        int month = (monthParam != null) ? Integer.parseInt(monthParam) : Calendar.getInstance().get(Calendar.MONTH);

        if (month < 0) {
            month = 11;
            year--;
        } else if (month > 11) {
            month = 0;
            year++;
        }

        // イベントが送信された場合、イベント情報を保存
        if (dateParam != null && eventParam != null) {
            String eventKey = year + "-" + (month + 1) + "-" + dateParam;
            events.put(eventKey, events.getOrDefault(eventKey, "") + eventParam + "<br>");
        }

        // 休暇申請があった場合、その日を休暇としてマーク
        if ("true".equals(vacationParam) && dateParam != null) {
            String vacationKey = year + "-" + (month + 1) + "-" + dateParam;
            vacations.put(vacationKey, true);  // 休暇を申請
        }
        
        

        // 受け取った情報をJSPに渡す
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("vacations", vacations);  // 休暇情報をJSPに渡す
        request.setAttribute("events", events);  // イベント情報をJSPに渡す

        // 日付指定がある場合はイベントページ、それ以外はカレンダーページを表示
        request.getRequestDispatcher((dateParam == null) ? "/WEB-INF/jsp/calender.jsp" : "/WEB-INF/jsp/event.jsp").forward(request, response);
    }
}
