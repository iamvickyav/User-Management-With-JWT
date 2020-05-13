package com.iamvickyav.usermanagement.model

import javax.validation.constraints.NotEmpty

class ChangePasswordRequest {

    @NotEmpty
    String email
}
