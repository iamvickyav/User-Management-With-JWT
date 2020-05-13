package com.iamvickyav.usermanagement.model

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class EmployeeUpdateRequest {

    @NotNull
    Integer id

    @NotEmpty
    String dept
}
