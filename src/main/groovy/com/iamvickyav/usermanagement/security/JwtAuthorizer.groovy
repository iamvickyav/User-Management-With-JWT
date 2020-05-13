package com.iamvickyav.usermanagement.security

import com.iamvickyav.usermanagement.exception.BusinessException
import com.iamvickyav.usermanagement.exception.ErrorCode
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtAuthorizer implements Authorizer {

    @Value('${jwt.token.expiry}')
    Integer expiryInSeconds

    @Value('${jwt.token.secret}')
    String jwtSecret

    @Override
    Claims validateToken(String jwtToken) {
        try {
            String token = jwtToken.replaceAll("Bearer ", "")
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody()
            return claims
        } catch (Exception ex) {
            throw new BusinessException(ErrorCode.INVALID_HEADER, "Invalid Authorization header", ex)
        }
    }

    @Override
    String generateToken(String email) {
        String jwtToken = Jwts.builder().setSubject(email)
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .setExpiration(new Date(System.currentTimeMillis() + expiryInSeconds * 1000))
                .compact()
        return jwtToken
    }
}
