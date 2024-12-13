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

        // イベントと休暇情報を取得
        String event = (String) request.getAttribute("event");
        String vacation = (String) request.getAttribute("vacation");
    %>

    <h2><%= year %>年<%= month + 1 %>月<%= date %>日のイベント</h2>
    
    <!-- 休暇申請フォーム -->
    <form action="eventServlet" method="get" onsubmit="return confirm('休暇申請を行います。よろしいですか？');">
        <input type="hidden" name="year" value="<%= year %>">
        <input type="hidden" name="month" value="<%= month %>">
        <input type="hidden" name="date" value="<%= date %>">
        <input type="hidden" name="vacation" value="true">
        <label for="reason">休暇事由:</label>
        <input type="text" name="reason" required>
        <button type="submit">休暇申請</button>
    </form>

    <!-- イベント追加フォーム -->
    <form action="eventServlet" method="get">
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

    <!-- イベント表示 -->
    <h3>登録されたイベント:</h3>
    <p><%= (event != null && !event.equals("なし")) ? event : "イベントは登録されていません" %></p>

    <!-- 休暇情報表示 -->
    <h3>休暇申請:</h3>
    <p><%= (vacation != null && !vacation.equals("なし")) ? vacation : "休暇申請はありません" %></p>

    <!-- イベント削除フォーム -->
    <form action="eventServlet" method="get" onsubmit="return confirm('このイベントを削除します。よろしいですか？');">
        <input type="hidden" name="year" value="<%= year %>">
        <input type="hidden" name="month" value="<%= month %>">
        <input type="hidden" name="date" value="<%= date %>">
        <input type="hidden" name="deleteEvent" value="true">
        <button type="submit">イベント削除</button>
    </form>

    <br>
    <a href="CalendarServlet?year=<%= year %>&month=<%= month %>">カレンダーに戻る</a>
</body>
</html>
