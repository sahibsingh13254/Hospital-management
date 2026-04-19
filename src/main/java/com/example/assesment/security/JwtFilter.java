package com.example.assesment.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


// runs once per request
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil; // to extract and validate token
    @Autowired
    private CustomUserDetailsService userDetailsService;   // to load user details from DB

    @Override
    protected void doFilterInternal(HttpServletRequest request, //   // incoming request — has headers, body etc
                                    HttpServletResponse response, // Outgoing response
                                    FilterChain filterChain)   // chain of filters
    throws ServletException, IOException{
        // Step 1: Read Authorization header
        String authHeader = request.getHeader("Authorization"); // reads: "Bearer eyJhbGci...",If no header than nuull
        String token = null;
        String username = null;

        // Step 2: Extract token from header
        if(authHeader != null  && authHeader.startsWith("Bearer")) { // token always starts with "Bearer ", that's a standard

            token = authHeader.substring(7);  // remove 7 chars "Bearer " = 7 chars, leaves just the token!!

            username = jwtUtil.extractUsername(token);  // reads username from inside token, "Sahib"
        }

        // Step 3: Validate and Set Authentication
        if(username != null    //  token was present and username extracted
                && SecurityContextHolder.getContext().getAuthentication() == null){ //user not already authenticated!,avoid setting twice!

            // load full user details from DB
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);  // gets username, password, roles from DB
            if(jwtUtil.validateToken(token)) { // is token valid? not expired? not fake?

                // create authentication object
                UsernamePasswordAuthenticationToken authToken  //   // create authentication object
                        = new UsernamePasswordAuthenticationToken(userDetails, // who is authenticated
                        null, //  credentials — null because, we already verified via token!
                        userDetails.getAuthorities()); // ROLE_ADMIN or ROLE_USER

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // attach request details to auth token

                // PUT USER IN SECURITY CONTEXT!
                SecurityContextHolder.getContext().setAuthentication(authToken);
                // Now Spring knows:
                // "Sahib" is making this request
                // with ROLE_USER ✅
            }



        }
        // Step 4: Continue to next filter/Controller
        filterChain.doFilter(request,response);
        // MUST call this!
        // without this → request never reaches Controller!
        // like guard opening the door after checking ID ✅



    }

}
