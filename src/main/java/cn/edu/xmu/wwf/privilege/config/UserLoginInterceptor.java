package cn.edu.xmu.wwf.privilege.config;


import cn.edu.xmu.wwf.privilege.utils.JWTUtils;
import cn.edu.xmu.wwf.privilege.utils.ReturnNo;
import cn.edu.xmu.wwf.privilege.utils.ReturnObject;
import com.alibaba.fastjson.JSON;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod().toString())) {
            return true; //true是直接放行，前端抓包会有options请求
            //false拒接访问，抓包就不会有options请求了
        }
        String token = request.getHeader("token");
        System.out.println(token);
        //token不存在
        if (token == null || token.equals(""))
        {
            response.setContentType("response.setContentType(\"application/json; charset=utf-8\");");
            PrintWriter out = null ;
            out = response.getWriter();
            out.write(JSON.toJSONString(new ReturnObject(ReturnNo.UNAUTHORIZED,"non-login")));
            out.flush();
            out.close();
            return false;
        }
        //验证token
        String sub = JWTUtils.validateToken(token);
        if (sub == null || sub.equals(""))
        {
            response.setContentType("response.setContentType(\"application/json; charset=utf-8\");");
            PrintWriter out = null ;
            out = response.getWriter();
            out.write(JSON.toJSONString(new ReturnObject(ReturnNo.FORBIDDEN,"token-illegal")));
            out.flush();
            out.close();
            return false;
        }
        //更新token有效时间 (如果需要更新其实就是产生一个新的token)
        if (JWTUtils.isNeedUpdate(token)){
            String newToken = JWTUtils.createToken(sub);
            response.setHeader(JWTUtils.USER_LOGIN_TOKEN,newToken);
        }
        return true;
    }
}