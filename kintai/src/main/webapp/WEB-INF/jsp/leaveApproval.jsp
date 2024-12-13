<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>休暇承認</title>
</head>
<body>
    <h1>休暇承認</h1>
    <form action="approveLeave" method="post">
        <label>申請ID:</label>
        <input type="text" name="requestId" required><br>
        <input type="submit" name="action" value="承認">
        <input type="submit" name="action" value="却下">
    </form>
    <a href="dashboard.jsp">ダッシュボードに戻る</a>
</body>
</html>
