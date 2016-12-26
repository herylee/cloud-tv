package joshua.cloudtv.interceptor;

import joshua.cloudtv.dao.model.User;
import joshua.cloudtv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by JoshuaShaw on 2016/12/16.
 */
public class MyselfInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    // 通过cookie的uuid获得user实例
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String uuid = (String) httpServletRequest.getSession().getAttribute("uuid");
        User myself = userService.getSessionUser(uuid);
        // 这个很重要
        if(myself!=null && modelAndView!=null)
            modelAndView.addObject("myself", myself);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // 打log
    }
}
