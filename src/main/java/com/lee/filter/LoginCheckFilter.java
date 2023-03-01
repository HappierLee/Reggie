package com.lee.filter;

import com.alibaba.fastjson.JSON;
import com.lee.common.BaseContext;
import com.lee.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否已经登录
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    public static  final AntPathMatcher pathMatcher = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        log.info("拦截到请求{}",requestURI);
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources",
                "/v2/api-docs"
        };
        boolean check = check(urls, requestURI);
        if(check){
            filterChain.doFilter(request,response);
            return;
        }
        if(request.getSession().getAttribute("employee") != null){

            Long userId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(userId);

            filterChain.doFilter(request,response);
            return;
        }
        if(request.getSession().getAttribute("user") != null){

            Long employeeId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(employeeId);

            filterChain.doFilter(request,response);
            return;
        }
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return ;
    }

    /**
     * 路径匹配，检查本次请求是否放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI){
        for(String url : urls){
            boolean match = pathMatcher.match(url, requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }
}
