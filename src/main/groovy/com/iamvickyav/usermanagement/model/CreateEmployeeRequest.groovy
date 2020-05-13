package com.iamvickyav.usermanagement.model

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class CreateEmployeeRequest {

    @NotEmpty
    String name

    @NotEmpty
    String dept

    @NotEmpty
    @Email
    String email
}
