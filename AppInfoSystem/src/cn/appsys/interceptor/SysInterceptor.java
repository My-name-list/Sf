package cn.appsys.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.appsys.pojo.BackendUser;
import cn.appsys.pojo.DevUser;
import cn.appsys.tools.Constants;

public class SysInterceptor extends HandlerInterceptorAdapter{

	private Logger logger = Logger.getLogger(SysInterceptor.class);
	@Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
            logger.info("SysInterceptor preHandle============");
            HttpSession session = request.getSession();
            BackendUser backendUser = (BackendUser)session.getAttribute(Constants.USER_SESSION);
            DevUser devUser = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);
            if(backendUser != null){
                    return true;
            }else if(devUser != null){
                    return true;
            }else{
                    response.sendRedirect(request.getContextPath()+"/403.jsp");
                    return false;
            }
    }
}
