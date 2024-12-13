<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>カレンダー</title>
    <style>
        table { 
            border-collapse: collapse; 
            width: 60%; 
            table-layout: fixed; 
        }
        th, td { 
            border: 1px solid black; 
            width: 14.28%; 
            height: 80px; 
            text-align: center; 
            vertical-align: middle; 
        }
        th { 
            background-color: #f0f0f0; 
        }
        .vacation { background-color: pink; }
        .sunday { color: red; font-weight: bold; background-color: tomato;}
        .saturday { color: blue; font-weight: bold; background-color: tomato;}
    </style>
</head>
<body>
    <% 
        int year = (int) request.getAttribute("year");
        int month = (int) request.getAttribute("month");
        Map<String, Boolean> vacations = (Map<String, Boolean>) request.getAttribute("vacations");

        // 曜日名の配列
        String[] weekdays = { "日", "月", "火", "水", "木", "金", "土" };
        
        // 月の最初の日の曜日を計算
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, 1);
        int firstDayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);  // 月初の曜日
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);  // 月の日数
    %>

    <h1><%= year %>年<%= month + 1 %>月のカレンダー</h1>

    <table>
        <!-- 曜日ヘッダー -->
        <tr>
            <% for (String day : weekdays) { %>
                <th><%= day %></th>
            <% } %>
        </tr>

        <% 
            int dayCounter = 1;
            boolean isPrinting = false; // 日付の印刷開始を示すフラグ
        %>

        <!-- カレンダーの日付 -->
        <% for (int row = 0; row < 6; row++) { %>
            <tr>
                <% for (int col = 1; col <= 7; col++) { 
                    String dayClass = "";

                    if (row == 0 && col == firstDayOfWeek) {
                        isPrinting = true; // 月初日が見つかったら印刷開始
                    }

                    if (isPrinting && dayCounter <= daysInMonth) {
                        String dateKey = year + "-" + (month + 1) + "-" + dayCounter;
                        boolean isVacation = vacations != null && vacations.getOrDefault(dateKey, false);

                        // 土曜と日曜の色付け
                        if (col == 1) {
                            dayClass = "sunday";  // 日曜日
                        } else if (col == 7) {
                            dayClass = "saturday";  // 土曜日
                        }
                %>
                    <td class="<%= dayClass %> <%= isVacation ? "vacation" : "" %>">
                        <a href="CalendarServlet?year=<%= year %>&month=<%= month %>&date=<%= dayCounter %>">
                            <%= dayCounter %>
                        </a>
                    </td>
                    <% dayCounter++; %>
                <% } else { %>
                    <td></td>
                <% } %>
                <% } %>
            </tr>
        <% } %>
    </table>

    <a href="CalendarServlet?year=<%= year %>&month=<%= month - 1 %>">前の月</a>
    <a href="CalendarServlet?year=<%= year %>&month=<%= month + 1 %>">次の月</a>
    
    <a href="<%= request.getContextPath() %>/DashboardServlet" class="button">ダッシュボードに戻る</a>
</body>
</html>
