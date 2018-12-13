package controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

import static util.AllConstants.COOKIE_VALUE;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 18.10.2012
 * Time: 22:01:08
 * To change this template use File | Settings | File Templates.
 */
public class LogoutServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();
        Cookie cookie = new Cookie(COOKIE_VALUE, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect(request.getContextPath());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

