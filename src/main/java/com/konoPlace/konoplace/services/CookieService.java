package com.konoPlace.konoplace.services;

import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookieService {

    public boolean verifyCookie(HttpServletRequest res) {
        return true;
    }

    public void setCookie(HttpServletResponse res , String idUser){
        // create a cookie
        Cookie cookie = new Cookie("userID", idUser);
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        //add cookie to response
        res.addCookie(cookie);

    }

    public String readCookie(HttpServletRequest request) {
        Cookie userID = WebUtils.getCookie(request, "userID");
        String cookie = userID.getValue();
        return cookie;
    }


}
