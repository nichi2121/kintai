package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ユーザーから送信された情報を取得
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 認証ロジックを仮定（実際の認証はデータベース等で行う）
        if (isValidUser(username, password)) {
            // 認証成功、ダッシュボードにリダイレクト
        	// 認証成功後にダッシュボードサーブレットへリダイレクト
        	response.sendRedirect(request.getContextPath() + "/DashboardServlet");

        } else {
            // 認証失敗、エラーメッセージを表示
            request.setAttribute("errorMessage", "ユーザー名またはパスワードが間違っています。");
            request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }

    // 仮のユーザー認証メソッド（実際はデータベース等を使用）
    private boolean isValidUser(String username, String password) {
        // ここではユーザー名とパスワードが一致するかを仮でチェック
        return "admin".equals(username) && "1234".equals(password);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // サーバー側で処理を行い、login.jsp に転送
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }
}
