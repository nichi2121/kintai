<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ユーザー登録</title>
</head>
<body>
    <h1>ユーザー登録</h1>
    <form action="register" method="post">
        <label>ユーザー名:</label>
        <input type="text" name="username" required><br>
        <label>パスワード:</label>
        <input type="password" name="password" required><br>
        <input type="submit" value="登録">
    </form>
    <a href="index.jsp">トップページへ戻る</a>
</body>
</html>
