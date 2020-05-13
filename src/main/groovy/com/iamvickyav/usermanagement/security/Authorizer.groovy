package com.iamvickyav.usermanagement.security

import io.jsonwebtoken.Claims

interface Authorizer {
    Claims validateToken(String jwtToken)
    String generateToken(String email)
}