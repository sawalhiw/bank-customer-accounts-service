package bank.config.security;

import bank.dto.Role;
import bank.feign.BankAuthServiceClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final BankAuthServiceClient authServiceClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TokenAuthenticationFilter(BankAuthServiceClient authServiceClient) {
        this.authServiceClient = authServiceClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null && !token.isEmpty()) {
            ResponseEntity<?> responseEntity = authServiceClient.validateToken(token);
            if (!responseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
                throw new CredentialsExpiredException("Authentication Failed.");
            }
            final Map<String, Object> map = extractResponseBody(responseEntity);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(map.get("username"), null,
                            Collections.singletonList(Role.valueOf((String) map.get("role"))));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        chain.doFilter(request, response);
    }

    private Map<String, Object> extractResponseBody(ResponseEntity<?> responseEntity) {
        try {
            String json = objectMapper.writeValueAsString(responseEntity.getBody());
            return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        } catch (Exception exception) {
            throw new IllegalStateException("Cannot convert body to map.");
        }
    }
}