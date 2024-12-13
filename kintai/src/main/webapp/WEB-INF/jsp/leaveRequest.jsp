<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>休暇申請</title>
</head>
<body>
    <h1>休暇申請</h1>
    <form action="submitLeaveRequest" method="post">
        <label>休暇開始日:</label>
        <input type="date" name="startDate" required><br>
        <label>休暇終了日:</label>
        <input type="date" name="endDate" required><br>
        <input type="submit" value="申請">
    </form>
    <a href="dashboard.jsp">ダッシュボードに戻る</a>
</body>
</html>
