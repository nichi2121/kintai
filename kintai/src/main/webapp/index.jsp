<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>トップページ</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
</head>
<body>
    <header>
        <h1>トップページ</h1>
    </header>
    <main>
        <div class="menu">
            <!-- ユーザー登録画面へのリンク (サーブレット経由) -->
            <a href="<%= request.getContextPath() %>/register" class="button">ユーザー登録</a>

            <!-- ログイン画面へのリンク (サーブレット経由) -->
            <a href="<%= request.getContextPath() %>/login" class="button">ログイン</a>
        </div>
    </main>
    <footer>
        <p>&copy; 2024 勤怠管理システム</p>
    </footer>
    <script src="<%= request.getContextPath() %>/scripts.js"></script>
</body>
</html>
