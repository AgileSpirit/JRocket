package infra.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(CorsFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // CORS "pre-flight" request
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With");
        response.addHeader("Access-Control-Allow-Headers", "GET, PUT, OPTIONS, X-XSRF-TOKEN");
        filterChain.doFilter(request, response);
    }

}
