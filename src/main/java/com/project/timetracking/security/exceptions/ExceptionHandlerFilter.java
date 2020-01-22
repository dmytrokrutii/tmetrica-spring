package com.project.timetracking.security.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.timetracking.wrapper.GeneralResponseWrapper;
import com.project.timetracking.wrapper.Status;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter that catches and handles exceptions.
 * Is applied in {@link com.project.timetracking.config.WebSecurityConfig} before {@link org.springframework.web.filter.CorsFilter}
 */
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (ExpiredJwtException e) {
            GeneralResponseWrapper errorResponse = new GeneralResponseWrapper(new Status(HttpStatus.UNAUTHORIZED.value()
                    , "Token expired"), null);
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.getWriter().write(convertObjectToJson(errorResponse));
        } catch (MalformedJwtException | SignatureException e) {
            GeneralResponseWrapper errorResponse = new GeneralResponseWrapper(new Status(HttpStatus.BAD_REQUEST.value()
                    , "Bad Token"), null);
            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            httpServletResponse.getWriter().write(convertObjectToJson(errorResponse));
        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        return mapper.writeValueAsString(object);
    }

}
