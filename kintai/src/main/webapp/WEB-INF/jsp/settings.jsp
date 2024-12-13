<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>設定</title>
</head>
<body>
    <h1>設定</h1>
    <form action="saveSettings" method="post">
        <label>通知の設定:</label>
        <input type="checkbox" name="notifications" checked> 有効<br>
        <input type="submit" value="保存">
    </form>
    <a href="dashboard.jsp">ダッシュボードに戻る</a>
</body>
</html>
