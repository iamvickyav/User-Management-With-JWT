package com.iamvickyav.usermanagement.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.iamvickyav.usermanagement.exception.BusinessException
import com.iamvickyav.usermanagement.exception.ErrorCode
import com.iamvickyav.usermanagement.exception.ErrorResponse
import com.iamvickyav.usermanagement.security.Authorizer
import io.jsonwebtoken.Claims
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    Authorizer authorizer

    @Autowired
    ObjectMapper objectMapper

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String jwtToken = httpServletRequest.getHeader("Authorization")

        try {
            if (jwtToken && jwtToken.startsWith("Bearer ")) {
                Claims claims = authorizer.validateToken(jwtToken)

                if (isTokenExpired(claims)) {
                    throw new BusinessException(ErrorCode.INVALID_TOKEN, "Authorization header is invalid")
                }

                httpServletRequest.setAttribute("claims", claims)
                filterChain.doFilter(httpServletRequest, httpServletResponse)
            } else {
                throw new BusinessException(ErrorCode.INVALID_HEADER, "Authorization header is invalid/not found")
            }
        } catch (BusinessException e) {
            logger.error("Authorization header is invalid/not found", e)
            ErrorResponse errorResponse = new ErrorResponse(code: e.errorCode.code, message:e.message)

            httpServletResponse.setContentType("application/json");
            httpServletResponse.setStatus(e.errorCode.httpStatus.value())
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(errorResponse))
        }
    }

    private Boolean isTokenExpired(Claims claims) {
        Date expirationDate = claims.getExpiration()
        Date currentDate = new Date()
        expirationDate.before(currentDate)
    }
}
