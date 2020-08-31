package edu.dwlx.intercepter;

import edu.dwlx.entity.User;
import edu.dwlx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomIntercepter implements HandlerInterceptor {
    @Autowired
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        StringBuffer url = request.getRequestURL();
        url.replace(0,22,"");
        String s = new String(url);
//        String s = url.toString();
        String[] content = s.split("/");
        int size = content.length;
        if(size == 4){
            if(content[1].equals("people")&&content[3].equals("edit")) {
                int uid = Integer.parseInt(content[2]);
                User user = userService.searchUserById(uid);
                if(user.getName().equals(name)){
                    System.out.println("intercepter: pass");
                    return true;
                }
                else
                    response.sendError(403, "禁止访问");
            }
        } else if(content[1].equals("people")){
            int uid = Integer.parseInt(content[2]);
            User user = userService.searchUserById(uid);
            if(user.getName().equals(name)) {
                System.out.println("intercepter: pass");
                return true;
            }
            else
                response.sendError(403, "禁止访问");
//            return user.getName().equals(name);
        }
        System.out.println("intercepter: fail");
        return false;
    }
}
