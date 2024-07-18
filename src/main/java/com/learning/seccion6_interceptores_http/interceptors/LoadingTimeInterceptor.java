package com.learning.seccion6_interceptores_http.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactoryFriend;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod controller = ((HandlerMethod)handler);
        logger.info("LoadingTimeInterceptor: preHandle()  entrando...." + controller.getMethod().getName() );
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        Random random = new Random();
        int delay = random.nextInt(500);
        Thread.sleep(delay);
        if (1>2){
            Map<String,String> map = new HashMap<String,String>();
            map.put("message", "Acceso no autorizado");
            map.put("date", new Date().toString());
            map.put("status","401 Unauthorized");
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(map);
            response.setContentType("application/json");
            response.setStatus(401);
            response.getWriter().write(jsonString);
            return false;
        }
        return true;
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        HandlerMethod controller = ((HandlerMethod)handler);
        long endTime = System.currentTimeMillis();
        long startTime = (long) request.getAttribute("startTime");
        long resultado = (endTime - startTime);
        logger.info("LoadingTimeInterceptor: preHandle()  saliendo...." + controller.getMethod().getName() );
        logger.info("Tiempo transcurrido..." + resultado + " milisegundos");
    }
}
