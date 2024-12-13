<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ダッシュボード</title>
</head>
<body>
    <h1>ダッシュボード</h1>
    <p>ようこそ、ユーザー名が認証されました！</p>
    <ul>
    	<!-- ダッシュボードの中にスケジュール管理へのリンクを追加 -->
        <li><a href="<%= request.getContextPath() %>/CalendarServlet" class="button">スケジュール管理</a></li>
        
        <li><a href="leaveRequest.jsp">休暇申請</a></li>
        <li><a href="leaveApproval.jsp">休暇承認（管理者のみ）</a></li>
        <li><a href="settings.jsp">設定</a></li>
        <li><a href="logout.jsp">ログアウト</a></li>
    </ul>
</body>
</html>
