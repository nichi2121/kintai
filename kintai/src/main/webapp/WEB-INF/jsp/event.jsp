<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>イベント管理</title>
</head>
<body>
    <% 
        int year = (int) request.getAttribute("year");
        int month = (int) request.getAttribute("month");
        String date = request.getParameter("date");
        Map<String, String> events = (Map<String, String>) request.getAttribute("events");
        Map<String, Boolean> vacations = (Map<String, Boolean>) request.getAttribute("vacations");
    %>

    <h2><%= year %>年<%= month + 1 %>月<%= date %>日のイベント</h2>
    
    <!-- 休暇申請フォーム -->
    <form action="CalendarServlet" method="get" onsubmit="return confirm('休暇申請を行います。よろしいですか？');">
        <input type="hidden" name="year" value="<%= year %>">
        <input type="hidden" name="month" value="<%= month %>">
        <input type="hidden" name="date" value="<%= date %>">
        <input type="hidden" name="vacation" value="true">
        <label for="reason">休暇事由:</label>
        <input type="text" name="reason" required>
        <button type="submit">休暇申請</button>
    </form>

    <!-- イベント追加フォーム -->
    <form action="CalendarServlet" method="get">
        <input type="hidden" name="year" value="<%= year %>">
        <input type="hidden" name="month" value="<%= month %>">
        <input type="hidden" name="date" value="<%= date %>">
        <label for="startTime">何時何分から:</label>
        <input type="time" name="startTime" required>
        <label for="endTime">何時何分まで:</label>
        <input type="time" name="endTime" required>
        <label for="event">イベントを追加:</label>
        <input type="text" name="event" required>
        <input type="submit" value="追加">
    </form>

    <h3>登録されたイベント:</h3>
    <% 
        String eventKey = year + "-" + (month + 1) + "-" + date;
        String event = events.getOrDefault(eventKey, "なし");
    %>
    <p><%= event %></p>

    <a href="CalendarServlet?year=<%= year %>&month=<%= month %>">カレンダーに戻る</a>
</body>
</html>
