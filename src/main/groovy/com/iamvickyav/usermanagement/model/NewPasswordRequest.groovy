package com.iamvickyav.usermanagement.model

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class NewPasswordRequest {

    @NotEmpty
    String email

    @NotEmpty
    @Email
    String newPassword

    @NotEmpty
    String tokenFromEmail
}
