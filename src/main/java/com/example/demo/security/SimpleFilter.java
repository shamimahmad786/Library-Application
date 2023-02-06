package com.example.demo.security;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class SimpleFilter implements Filter {
   

	
	@Override
   public void destroy() {}
   
//	@Autowired
//	PgiAuditRepository pgiAuditRepository1;

   @Override
   public void doFilter
      (ServletRequest request,final ServletResponse response, FilterChain filterchain) 
      throws IOException, ServletException {
	   
	   
	   
	   // System.out.println("calleded--->");
	   
	    HttpServletResponse res = (HttpServletResponse) response;
	    res.addHeader("X-XSS-Protection", "1; mode=block");
	    res.addHeader("X-Content-Type-Options", "nosniff");
	    res.addHeader("Content-Security-Policy", "default-src 'self'");
	    res.setHeader("Strict-Transport-Security", "max-age=7776000");
	    res.setHeader("Access-Control-Allow-Credentials", "true");
	    res.setHeader("X-Frame-Options", "SAMEORIGIN");
	    res.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET");
		res.setHeader("Access-Control-Max-Age", "86400");
		res.setHeader("Access-Control-Max-Age", "86400");
		res.setHeader("Access-Control-Allow-Origin", "*");
	
	    

	   
	  
	   
	   ServletContext context = ((HttpServletRequest) request).getSession().getServletContext();
	   
	   HttpServletRequest req = (HttpServletRequest) request;
	   
//	  // System.out.println("Private key--->"+context.getAttribute("_private_key"));
//	  // System.out.println("Public key---->"+context.getAttribute("_public_key"));
	   
	  
	   
	   
//	   final CopyPrintWriter writer = new CopyPrintWriter(response.getWriter());
	   filterchain.doFilter(request,response);
	 
//	   filterchain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse) response) {
//	        @Override public PrintWriter getWriter() {
//	            return writer;
//	        }
//	    });
//	   // System.out.println("writer--->"+writer.getCopy());
   }

   @Override
   public void init(FilterConfig filterconfig) throws ServletException {
	   
	   
	   
   }


}