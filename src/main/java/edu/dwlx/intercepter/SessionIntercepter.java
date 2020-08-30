package edu.dwlx.intercepter;

import edu.dwlx.Session.MySessionContext;
import edu.dwlx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SessionIntercepter implements HandlerInterceptor {
    @Autowired
    private MySessionContext mySessionContext;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for(Cookie c : cookies){
            if(c.getName().equals("sessionId"))
                cookie = c;
        }
        if(cookie != null){
            HttpSession session = mySessionContext.getSession(cookie.getValue());
            User user = (User) session.getAttribute("user");
            request.getSession().setAttribute("user", user);
            System.out.println("session interceptor: session id: " + cookie.getValue());
        }
        return true;
    }
}
