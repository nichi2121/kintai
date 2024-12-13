<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>スケジュール管理</title>
    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.css" rel="stylesheet">
</head>
<body>
    <header>
        <h1>スケジュール管理</h1>
    </header>

    <!-- イベント登録フォーム -->
    <form id="eventForm">
    	 <label for="eventTitle">イベント名:</label>
    <input type="text" id="eventTitle" name="title" required>

    <label for="eventDate">日付:</label>
    <input type="date" id="eventDate" name="date" required>

    <button type="submit">登録</button>
    
    
        <label>タイトル: <input type="text" id="title" required></label><br>
        <label>開始日: <input type="date" id="startDate" required></label><br>
        <label>終了日: <input type="date" id="endDate"></label><br>
        <button type="submit">イベントを追加</button>
    </form>

    <div id="calendar"></div>

    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var calendarEl = document.getElementById('calendar');

            // カレンダーの初期化
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'dayGridMonth',
                locale: 'ja',
                headerToolbar: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay'
                },
                events: '/events',  // サーバーからイベント取得
            });

            // イベント登録処理
            /* document.getElementById('eventForm').addEventListener('submit', function (e) {
                e.preventDefault();

                // 入力データを取得
                var title = document.getElementById('title').value;
                var startDate = document.getElementById('startDate').value;
                var endDate = document.getElementById('endDate').value || startDate;

                // データをサーバーに送信
                fetch('/events', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        title: title,
                        start: startDate,
                        end: endDate
                    })
                }).then(response => {
                    if (response.ok) {
                        calendar.refetchEvents();  // イベントを再読み込み
                        alert("イベントが追加されました！");
                        document.getElementById('eventForm').reset();
                    }
                });
            });

            calendar.render();  // カレンダー表示
        });
    </script>
</body>
</html>
