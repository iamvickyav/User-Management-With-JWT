package com.iamvickyav.usermanagement.model

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class LoginRequest {

    @NotEmpty
    String email

    @NotEmpty
    @Email
    String password
}
