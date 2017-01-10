package joshua.cloudtv.interceptor;

import joshua.cloudtv.constant.UserPermission;
import joshua.cloudtv.dao.model.User;
import joshua.cloudtv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    // 判断是否是admin
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uuid = (String) httpServletRequest.getSession().getAttribute("uuid");
        User admin = userService.getSessionUser(uuid);

        if(admin!=null && UserPermission.isAdmin(admin.getPermission()))
            return true;
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // 打log
    }
}