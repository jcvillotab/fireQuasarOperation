package com.firequasar.demo.config;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.firequasar.demo.utils.Constants;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class IdCookieFilter extends OncePerRequestFilter{

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
      Cookie[] cookies = request.getCookies();
        boolean hasUserIdCookie = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constants.COOKIE_NAME.equals(cookie.getName())) {
                    hasUserIdCookie = true;
                    break;
                }
            }
        }

        if (!hasUserIdCookie) {
            String userId = UUID.randomUUID().toString();
            Cookie userCookie = new Cookie(Constants.COOKIE_NAME, userId);
            userCookie.setMaxAge(60 * 60 * 24 * 365); 
            userCookie.setHttpOnly(true); 
            userCookie.setPath("/"); 
            response.addCookie(userCookie);
            logger.info("New User ID generated and cookie set: " + userId);
        }

        filterChain.doFilter(request, response);
    
  }
  
  
}
