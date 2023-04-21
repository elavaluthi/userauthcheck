//package com.example.demo;
//
//import java.security.Principal;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//@RestController
//@SpringBootApplication
//public class UserauthcheckApplication {
//		
//	@GetMapping
//    public String welcome() {
//        return "Welcome to Google !!";
//    }
//
//    @GetMapping("/user")
//    public Principal user(Principal principal) {
//        System.out.println("username : " + principal.getName());
//        return principal;
//    } 
//   
//    
//	public static void main(String[] args) {
//		SpringApplication.run(UserauthcheckApplication.class, args);
//	}
//
//}
package com.example.demo;

import java.security.Principal;

//import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@SpringBootApplication
public class UserauthcheckApplication {
    
    @GetMapping("/")
    public String welcome() {
        return "Welcome to Google !!";
    }

    @GetMapping("/user")
    public Principal user(Principal principal, HttpSession session) {
        // Check if the session is new or has expired
        if (session.isNew() || session.getAttribute("authenticated") == null) {
            // If the session is new or has expired, authenticate the user and store a session token
            session.setAttribute("authenticated", true);
            session.setMaxInactiveInterval(5 * 60); // 5 minutes
            System.out.println("User authenticated: " + principal.getName());
        }
        
        return principal;
    }
 


    public static void main(String[] args) {
        SpringApplication.run(UserauthcheckApplication.class, args);
    }
}