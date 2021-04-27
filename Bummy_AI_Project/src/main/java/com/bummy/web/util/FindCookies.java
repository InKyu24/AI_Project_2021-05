package com.bummy.web.util;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class FindCookies {
	
	public String FindCookie (HttpServletRequest request, String cook) throws IOException {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cook)) {
				return cookie.getValue();				
			}
		}
		return null;
	}
}
