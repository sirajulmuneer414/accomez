package com.sirajul.Accomez.CustomAnnotation.AnnotationImplementation;

import com.sirajul.Accomez.CustomAnnotation.RolesAllowed;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class RolesCheckAspect {

    private final HttpServletRequest request;

    public RolesCheckAspect(HttpServletRequest request){
        this.request = request;
    }

    @Before("@annotation(RolesAllowed)")
    public void checkRoles(JoinPoint joinpoint){

        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new SecurityException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);

        Claims claims = Jwts.parser()
                .setSigningKey("srj007001")
                .parseClaimsJws(token)
                .getBody();

        List<String> userRoles = claims.get("roles",List.class);

        MethodSignature signature =(MethodSignature) joinpoint.getSignature();

        Method method = signature.getMethod();

        RolesAllowed rolesAllowed = method.getAnnotation(RolesAllowed.class);

        List<String> requiredRoles = Arrays.asList(rolesAllowed.value());

        boolean hasRole = requiredRoles.stream().anyMatch(userRoles::contains);

        if(!hasRole) {
            throw new SecurityException("User does not have the required role");
        }
    }

    
}
