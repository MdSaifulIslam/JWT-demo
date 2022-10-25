package com.springlearn.crudDemp.utility;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springlearn.crudDemp.entity.User;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenUtility jwtTokenUtility;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if(!hasAuthorizationHeader(request)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String accessToken = getAccessToken(request);
		System.out.println("Access Token : ====>>>" + accessToken);
		
		if(!jwtTokenUtility.validateAccessToken(accessToken)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		setAuthenticationContext(accessToken, request);
		filterChain.doFilter(request, response);
	}

	private void setAuthenticationContext(String accessToken, HttpServletRequest request) {
		UserDetails userDetails = getUserDetails(accessToken);
		
		UsernamePasswordAuthenticationToken authentication=
				new UsernamePasswordAuthenticationToken(userDetails, null, null);
		
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private UserDetails getUserDetails(String accessToken) {
		User userDetails = new User();
		
		String [] subjectArray = jwtTokenUtility.getSubject(accessToken).split(",");
		userDetails.setUsername(subjectArray[0]);
		userDetails.setEmail(subjectArray[1]);
		return userDetails;
	}

	private boolean hasAuthorizationHeader(HttpServletRequest req) {
		String header = req.getHeader("Authorization");
		System.out.println("Header=====>>" + header);
		
		if(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
			System.out.println("not started with Bearer =>");
			return false;
		}
		
		return true;
	}
	
	private String getAccessToken(HttpServletRequest req) {
		String header = req.getHeader("Authorization");
		return header.split(" ")[1].trim();
	}

}
